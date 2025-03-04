import React, { FormEvent, useEffect, useState } from "react";
// import HomeImg from "./HomeImg";
// import jeju from "../../assets/images/1/jeju.jpg";
// import gapyeng from "../../assets/images/1/gapyeong.jpg";
// import busan from "../../assets/images/1/busan.jpg";
// import gyengju from "../../assets/images/1/gyengju.jpg";
// import seoul from "../../assets/images/1/seoul.jpg";
import { City, CityImg, CityName, CityWarp, GroupLabel, GroupLine, HomeBox, MostUsed, MostUsedBox, PopularCityBox, PriceDiv, ProductCity, ProductDetail, ProductImg, ProductName } from "./HomeSt";
import { Product, Top5Product } from "../../types/type";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import useSearchStore, { SearchData } from "../../stores/use.search.store";
import HomeImg from "./HomeImg";



export default function Home() {
  const [products, setProducts] = useState<Top5Product[]>([]);
  const { searchData, pushData } = useSearchStore();
  const navigate = useNavigate();

  useEffect(() => {
    try {
      axios.get(`http://localhost:8082/api/v1/products/top5`)
      .then((response) => {
        setProducts(response.data.data);
      })
    } catch(error) {
      console.error(error);
    }
  }, [])

  const handleProductClick = (id: number) => {
    navigate(`/detailProduct/${id}`);
    window.scrollTo(0, 0);
  };

  const handleCityClick = (cityName: string) => {
    pushData({
      ...searchData,
      city: cityName as SearchData['city']
    })
    navigate(`/allProductPage?city=${encodeURIComponent(cityName)}`);
    window.scrollTo(0, 0);
  };

  return (
    <>
      <HomeImg />
      {/* //! 홈 화면 */}
      <HomeBox>
        <GroupLine>
          <GroupLabel>국내 인기 여행지</GroupLabel>
        </GroupLine>
        <PopularCityBox>
          <City onClick={() => handleCityClick("제주도") }>
            <CityImg src={"/images/logo.png"} />
            <CityName>제주도</CityName>
          </City>
          <City onClick={() => handleCityClick('서울') }>
            <CityImg src={"/images/logo.png"} />
            <CityName>서울</CityName>
          </City>
          <City onClick={() => handleCityClick('부산') }>
            <CityImg src={"/images/logo.png"} />
            <CityName>부산</CityName>
          </City>
          <City onClick={() => handleCityClick('가평') }>
            <CityImg src={"/images/logo.png"} />
            <CityName>가평</CityName>
          </City>
          <City onClick={() => handleCityClick('경주') }>
            <CityImg src={"/images/logo.png"} />
            <CityName>경주</CityName>
          </City>
        </PopularCityBox>

        <GroupLine>
          <GroupLabel>인기 숙소</GroupLabel>
        </GroupLine>
        <MostUsedBox>
        {products.map(product => (
          <MostUsed key={product.id} onClick={() => handleProductClick(product.id)}>
            <ProductImg src={`http://localhost:8082/image/${product.productImage}`} alt="숙소 이미지"/>
            <ProductDetail>
              <CityWarp>
            <ProductCity>{product.productRegion} - </ProductCity>
            <ProductCity>{product.productCategory}</ProductCity>
              </CityWarp>
            <ProductName>{product.productName}</ProductName>
            <PriceDiv>{product.productPrice} 원</PriceDiv>
            </ProductDetail>
          </MostUsed>
        ))}
        </MostUsedBox>

        {/* <GroupLine>
          <GroupLabel>인기 레저 & 티켓</GroupLabel>
        </GroupLine>
        <MostTicketBox>
        {mainTicketProduct.map(tickets => (
          <MostTicket key={tickets.id}>
            <ProductImg src={tickets.img}/>
            <ProductDetail>
              <CityWarp>
              <ProductCity>{tickets.city} - </ProductCity>
              <ProductCity>{tickets.TicketCategory}</ProductCity>
              </CityWarp>
              <ProductName>{tickets.name}</ProductName>
              <PriceDiv>{tickets.price}</PriceDiv>
            </ProductDetail>
          </MostTicket>
        ))}

        </MostTicketBox> */}
      </HomeBox>
    </>
  );
}
