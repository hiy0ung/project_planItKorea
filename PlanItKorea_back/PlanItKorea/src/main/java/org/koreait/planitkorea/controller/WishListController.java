package org.koreait.planitkorea.controller;

import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.common.constant.ApiMappingPattern;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.wishList.response.WishListResponseDto;
import org.koreait.planitkorea.service.WishListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.WISHLIST)
@RequiredArgsConstructor
public class WishListController {

    private final WishListService wishListService;

    public static final String WISH_LIST_CREATE = "/create";
    public static final String WISH_LIST_GET = "/get";
    public static final String WISH_LIST_DELETE = "/delete/{wishListId}";


    @PostMapping(WISH_LIST_CREATE)
    public ResponseEntity<ResponseDto<Boolean>> addWishList(@AuthenticationPrincipal Long id, @RequestParam Long productId) {
        ResponseDto<Boolean> response =  wishListService.addWishList(id, productId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(WISH_LIST_GET)
    public ResponseEntity<ResponseDto<List<WishListResponseDto>>> getAllWishList(@AuthenticationPrincipal Long id) {
        ResponseDto<List<WishListResponseDto>> response = wishListService.getAllWishList(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(WISH_LIST_DELETE)
    public ResponseEntity<ResponseDto<Void>> deleteWishList(@AuthenticationPrincipal Long id, @PathVariable Long wishListId) {
        ResponseDto<Void> response = wishListService.deleteWishList(id, wishListId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
