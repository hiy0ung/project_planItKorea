import React, { FormEvent, useEffect, useState } from "react";
import { GroupLine } from "../Login/SignSt";
import {
  AllDiv,
  AllProductDiv,
  PriceDiv,
  ProductDetail,
  ProductDiv,
  ProductImg,
  ProductName,
  PageDiv,
} from "../Product/AllProductSt";
import ReactPaginate from "react-paginate";
import { Checkbox } from "@mui/material";
import { Product, WishListResponseDto } from "../../types/type";
import {
  Favorite,
  FavoriteBorder
} from "@mui/icons-material";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import { useCookies } from "react-cookie";
import ProductFilter from "./ProductFilter";

const ITEMS_PER_PAGE = 9;

export default function AllProductPage() {
  const [wishList, setWishList] = useState<WishListResponseDto[]>([]);
  const [userWishList, setUserWishList] = useState<number[]>([]);
  const [products, setProducts] = useState<Product[]>([]);
  const [currentPage, setCurrentPage] = useState<number>(0);

  const location = useLocation();
  const navigate = useNavigate();
  const searchParams = new URLSearchParams(location.search);

  const [cookies] = useCookies(["token"]);
  const token = cookies.token;

  useEffect(() => {
    fetchProducts();
    if (token) {
      fetchWishList();
    }
  }, [location]);

  const formatDate = (date: Date | null) => {
    if (!date) return "";
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, "0");
    const day = date.getDate().toString().padStart(2, "0");
    return `${year}-${month}-${day}`;
  }

  const getQueryParams = () => {
    const queryParams = new URLSearchParams(location.search);
    const cityName = queryParams.get("cityName") || "";
    const startDateParam = queryParams.get("startDate") || null;
    const endDateParam = queryParams.get("endDate") || null;
    const person = queryParams.get("person") ? parseInt(queryParams.get("person")!) : 0;

    const startDate = startDateParam ? new Date(startDateParam) : null;
    const endDate = endDateParam ? new Date(endDateParam) : null;
  
    return { cityName, startDate, endDate, person };
  };

  const fetchProducts = async () => {
    const { cityName, startDate, endDate, person } = getQueryParams();

    try {
      const response = await axios.get(
        `http://localhost:8082/api/v1/products/search`,
        {
          params: {
            cityName,
            startDate: startDate ? formatDate(startDate) : "",
            endDate: endDate ? formatDate(endDate) : "",
            person
          },
        });
        setProducts(response.data.data);
    } catch (error) {
      console.error("숙소 검색 중 오류 발생", error);
    }
  };

  
  const fetchWishList = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8082/api/v1/wishlist`, {
          headers: { Authorization: `Bearer ${token}` },
        });

        const wishListData: WishListResponseDto[] = response.data.data;

        setWishList(wishListData);
        setUserWishList(wishListData.map((item) => item.productId));

    } catch (error) {
      console.error("위시리스트 가져오기 중 오류 발생", error);
    }
  }

  //! 찜
  const toggleWishlist = async(productId: number) => {
    if(!token) {
      alert('로그인이 필요한 시스템입니다.')
      return;
    }
    try {
      const isWished = userWishList.includes(productId);
      if (isWished) {
        const wishListItem = wishList.find((item) => item.productId === productId);

        console.log("삭제 대상", wishListItem);
        if (!wishListItem) {
          console.error("삭제할 위시리스트 아이템을 찾을 수 없습니다.");
          return;
        }
        if (!wishListItem) return;

        await axios.delete(`http://localhost:8082/api/v1/wishlist/${wishListItem.wishListId}`, { headers: { Authorization: `Bearer ${token}` }});

        setWishList((prev) => prev.filter((item) => item.productId !== productId));
        setUserWishList((prev) => prev.filter((id) => id !== productId));
      } else {
        const response = await axios.post(`http://localhost:8082/api/v1/wishlist?productId=${productId}`, 
        {},
        { headers: { Authorization: `Bearer ${token}` }}
        );

        if (response.data.result) {
          const newWishItem: WishListResponseDto = {
            wishListId: response.data.data.wishListId,
            userId: response.data.data.userId,
            productId: productId,
            productName: response.data.data.productName,
            productAddress: response.data.data.productAddress,
            productPrice: response.data.data.productPrice,
            productImage: response.data.data.productImage,
          }

          setWishList((prev) => [...prev, newWishItem]);
          setUserWishList((prev) => [...prev, productId]);
        }
      }
      console.log('위시리스트가 성공적으로 업데이트되었습니다.');
    } catch (error) {
      console.error('위시리스트 업데이트 중 오류 발생:', error);
    }
  };

  const label = { inputProps: { "aria-label": "Checkbox demo" } };

  //! 페이지네이션
  const handlePageChange = (event: { selected: number }) => {
    setCurrentPage(event.selected);
  };

  const indexOfLastItem = (currentPage + 1) * ITEMS_PER_PAGE;
  const indexOfFirstItem = indexOfLastItem - ITEMS_PER_PAGE;
  const currentItems = products.slice(indexOfFirstItem, indexOfLastItem);

  const handleProductClick = (productId: number) => {
    const queryParams = new URLSearchParams({
      startDate: searchParams.get("startDate") || "",
      endDate: searchParams.get("endDate") || "",
      person: searchParams.get("person") || "1",
    }).toString();
  
    navigate(`/detailProduct/${productId}?${queryParams}`);
  };

  return (
    <>
      <GroupLine />
      <AllDiv>
        <ProductFilter />
        <AllProductDiv>
          {currentItems.map((product) => (
            <ProductDiv
              key={product.productId}
              onClick={() => handleProductClick(product.productId)}
            >
              <ProductImg src={product.productImage} />
              <ProductDetail>
                  <Checkbox
                    {...label}
                    icon={<FavoriteBorder sx={{ color: '#DD1162',  }} />}
                    checkedIcon={<Favorite sx={{ color: '#DD1162' , }} />}
                    checked={userWishList.includes(product.productId)}
                    onClick={(e) => {
                      e.stopPropagation()
                      toggleWishlist(product.productId);
                    }}
                    sx={{ position: 'relative'}}
                  />
                <ProductName>{product.productName}</ProductName>
                <PriceDiv>₩ {product.productPrice.toLocaleString()}</PriceDiv>
              </ProductDetail>
            </ProductDiv>
          ))}
          <PageDiv>
            <ReactPaginate
              previousLabel={"<"}
              nextLabel={">"}
              breakLabel={"..."}
              pageCount={Math.ceil(products.length / ITEMS_PER_PAGE)}
              marginPagesDisplayed={2}
              pageRangeDisplayed={5}
              onPageChange={handlePageChange}
              containerClassName={"pagination"}
              pageClassName={"page-item"}
              pageLinkClassName={"page-link"}
              previousClassName={"page-item"}
              previousLinkClassName={"page-link"}
              nextClassName={"page-item"}
              nextLinkClassName={"page-link"}
              breakClassName={"page-item"}
              breakLinkClassName={"page-link"}
              activeClassName={"active"}
            />
          </PageDiv>
        </AllProductDiv>
      </AllDiv>
    </>
  );
}
