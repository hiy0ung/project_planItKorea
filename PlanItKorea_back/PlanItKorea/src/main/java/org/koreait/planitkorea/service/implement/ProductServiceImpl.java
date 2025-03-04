package org.koreait.planitkorea.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.common.constant.ResponseMessage;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.product.response.*;
import org.koreait.planitkorea.entity.Facility;
import org.koreait.planitkorea.entity.Product;
import org.koreait.planitkorea.repository.ProductRepository;
import org.koreait.planitkorea.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ResponseDto<List<ProductListResponseDto>> searchAllProduct(String cityName, int person, LocalDate startDate, LocalDate endDate) {
        List<ProductListResponseDto> data = null;
        try {
            List<Object[]> results = productRepository.findAllProductsByCityAndDate(cityName, person, startDate, endDate);

            data = results.stream()
                    .map(result -> new ProductListResponseDto(
                            (Long) result[0],
                            (String) result[1],
                            (String) result[2],
                            (String) result[3],
                            (String) result[4],
                            (String) result[5],
                            (String) result[6],
                            Arrays.stream(((String) result[7]).split(","))
                                    .map(Long::valueOf)
                                    .collect(Collectors.toList())
                    )).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<ProductDetailResponseDto> getProductDetail(Long productId) {
        ProductDetailResponseDto data = null;

        try {
            List<Object[]> result = productRepository.findProductDetailById(productId);

            data = new ProductDetailResponseDto(
                    ((Long) result.get(0)[0]),
                    (String) result.get(0)[1],
                    (String) result.get(0)[2],
                    (String) result.get(0)[3],
                    (String) result.get(0)[4],
                    (String) result.get(0)[5],
                    result.stream()
                            .map(row -> (String) row[6])
                            .distinct()
                            .collect(Collectors.toList()),
                    result.stream()
                            .map(row -> new SubProductResponseDto(
                                    (Long) row[7],
                                    (String) row[8],
                                    (String) row[9],
                                    (int) row[10],
                                    List.of((String) row[11])
                            ))
                            .distinct()
                            .collect(Collectors.toList()),
                    result.stream()
                            .map(row -> new FacilityResponseDto(
                                    (Long) row[12],
                                    (String) row[13]
                            ))
                            .distinct()
                            .collect(Collectors.toList())
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<Top5ResponseDto>> getTop5Product() {
        List<Top5ResponseDto> data = null;

        try {

            List<Object[]> results = productRepository.findTop5Products();

            data = results.stream()
                    .map(result -> new Top5ResponseDto(
                            (Long) result[0],
                            (String) result[1],
                            (String) result[2],
                            (String) result[3],
                            (String) result[4],
                            (String) result[5],
                            (String) result[6]

                    )).collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<ProductListResponseDto>> getProductByCity(String popularRegion) {
        List<ProductListResponseDto> data = null;

        try {
            List<Product> products = productRepository.findAll();
            data = products.stream()
                    .filter(product -> product.getProductCities().stream()
                            .anyMatch(productCity -> productCity.getCity().getCityName().equals(popularRegion)))
                    .map(product -> new ProductListResponseDto(
                            product.getId(),
                            product.getProductCategory(),
                            product.getProductName(),
                            product.getProductPrice(),
                            product.getProductDescription(),
                            product.getProductAddress(),
                            product.getProductImages().get(0).getProductImage(),
                            product.getFacilities().stream()
                                    .map(Facility::getId)
                                    .collect(Collectors.toList())
                    )).collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}