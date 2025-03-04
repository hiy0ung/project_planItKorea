package org.koreait.planitkorea.controller;

import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.dto.product.response.ProductDetailResponseDto;
import org.koreait.planitkorea.dto.product.response.ProductListResponseDto;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.product.response.Top5ResponseDto;
import org.koreait.planitkorea.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    public static final String TOP5 = "top5";

    @GetMapping("/search")
    public ResponseEntity<ResponseDto<List<ProductListResponseDto>>> searchAllProduct(
            @RequestParam String cityName,
            @RequestParam int person,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        ResponseDto<List<ProductListResponseDto>> response = productService.searchAllProduct(cityName, person, startDate, endDate);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ResponseDto<ProductDetailResponseDto>> getProductDetail(@PathVariable Long productId) {
        ResponseDto<ProductDetailResponseDto> response = productService.getProductDetail(productId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(TOP5)
    public ResponseEntity<ResponseDto<List<Top5ResponseDto>>> getTop5Product() {
        ResponseDto<List<Top5ResponseDto>> response = productService.getTop5Product();
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<ProductListResponseDto>>> getProductByCity(@RequestParam String popularRegion) {
        ResponseDto<List<ProductListResponseDto>> response = productService.getProductByCity(popularRegion);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}