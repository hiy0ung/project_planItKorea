package org.koreait.planitkorea.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.common.constant.ResponseMessage;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.wishList.response.WishListResponseDto;
import org.koreait.planitkorea.entity.Product;
import org.koreait.planitkorea.entity.User;
import org.koreait.planitkorea.entity.WishList;
import org.koreait.planitkorea.repository.ProductRepository;
import org.koreait.planitkorea.repository.UserRepository;
import org.koreait.planitkorea.repository.WishListRepository;
import org.koreait.planitkorea.service.WishListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final WishListRepository wishListRepository;

    @Override
    public ResponseDto<Boolean> addWishList(Long id, Long productId) {

        try {
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isEmpty()) {
                ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            }

            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isEmpty()) {
                ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
            User user = userOptional.get();
            Product product = productOptional.get();

            WishList wishList = new WishList();
            wishList.setUser(user);
            wishList.setProduct(product);
            wishListRepository.save(wishList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, true);
    }

    @Override
    public ResponseDto<List<WishListResponseDto>> getAllWishList(Long id) {
        List<WishListResponseDto> data = null;

        try {
            List<Object[]> convertDto = wishListRepository.getWishListByUserId(id);

            data = convertDto.stream().map(dto -> new WishListResponseDto(
                    (Long) dto[0],
                    (Long) dto[1],
                    (String) dto[2],
                    (String) dto[3],
                    (String) dto[4],
                    (String) dto[5]
            )).collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Void> deleteWishList(Long id, Long wishListId) {

        try {
            Optional<WishList> wishListOptional = wishListRepository.findByUserIdAndId(id, wishListId);

            wishListRepository.delete(wishListOptional.get());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}
