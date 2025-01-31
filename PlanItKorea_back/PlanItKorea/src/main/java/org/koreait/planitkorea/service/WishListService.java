package org.koreait.planitkorea.service;

import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.wishList.response.WishListResponseDto;

import java.util.List;

public interface WishListService {
    ResponseDto<Boolean> addWishList(Long id, Long wishListId);

    ResponseDto<List<WishListResponseDto>> getAllWishList(Long id);

    ResponseDto<Void> deleteWishList(Long id, Long productId);
}
