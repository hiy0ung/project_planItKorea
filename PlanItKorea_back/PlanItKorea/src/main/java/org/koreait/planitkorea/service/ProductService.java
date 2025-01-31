package org.koreait.planitkorea.service;

import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.product.request.ProductRequestDto;
import org.koreait.planitkorea.dto.product.response.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ResponseDto<List<ProductResponseDto>> searchAllProduct(ProductRequestDto dto);
}
