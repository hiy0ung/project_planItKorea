package org.koreait.planitkorea.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.common.constant.ResponseMessage;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.product.request.ProductRequestDto;
import org.koreait.planitkorea.dto.product.response.ProductResponseDto;
import org.koreait.planitkorea.repository.ProductRepository;
import org.koreait.planitkorea.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ResponseDto<List<ProductResponseDto>> searchAllProduct(ProductRequestDto dto) {
        List<ProductResponseDto> data = null;
        try {
            String cityName = dto.getCityName();
            int person = dto.getPerson();
            LocalDate startDate = dto.getStartDate();
            LocalDate endDate = dto.getEndDate();

            List<Object[]> results = productRepository.findAllProductsByCityAndDate(cityName, person, startDate, endDate);

            data = results.stream()
                    .map(result -> new ProductResponseDto(
                            (Long) result[0],
                            (String) result[1],
                            (String) result[2],
                            (String) result[3],
                            (String) result[4],
                            (Long) result[5],
                            (String) result[6],
                            (String) result[7],
                            (int) result[8],
                            (String) result[9]
                    )).collect(Collectors.toList());
        } catch (Exception e) {
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
