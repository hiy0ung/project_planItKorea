package org.koreait.planitkorea.controller;

import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.dto.product.request.ProductRequestDto;
import org.koreait.planitkorea.dto.product.response.ProductResponseDto;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/search")
    public ResponseEntity<ResponseDto<List<ProductResponseDto>>> searchAllProduct(@RequestBody ProductRequestDto dto) {
        ResponseDto<List<ProductResponseDto>> response = productService.searchAllProduct(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
