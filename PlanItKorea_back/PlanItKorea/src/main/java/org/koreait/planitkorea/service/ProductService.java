package org.koreait.planitkorea.service;

import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.product.response.ProductDetailResponseDto;
import org.koreait.planitkorea.dto.product.response.ProductListResponseDto;
import org.koreait.planitkorea.dto.product.response.Top5ResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface ProductService {
    ResponseDto<List<ProductListResponseDto>> searchAllProduct(String cityName, int person, LocalDate startDate, LocalDate endDate);

    ResponseDto<ProductDetailResponseDto> getProductDetail(Long productId);

    ResponseDto<List<Top5ResponseDto>> getTop5Product();

    ResponseDto<List<ProductListResponseDto>> getProductByCity(String popularRegion);
}