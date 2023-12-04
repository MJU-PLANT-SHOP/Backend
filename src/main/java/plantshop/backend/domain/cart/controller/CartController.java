package plantshop.backend.domain.cart.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import plantshop.backend.domain.cart.dto.request.CartRequestDto;
import plantshop.backend.domain.cart.dto.response.GetCartListResponseDto;
import plantshop.backend.domain.cart.service.CartService;
import plantshop.backend.response.BaseResponse;
import plantshop.backend.response.DataResponse;

import java.util.List;

import static plantshop.backend.response.SuccessInfo.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carts")
@Tag(name = "장바구니 API 문서", description = "장바구니 API 문서")
public class CartController {
    private final CartService cartService;

    @Operation(summary = "장바구니 추가 API", description = "장바구니에 물품 추가 API")
    @PostMapping()
    public BaseResponse addToCart(@Valid @RequestBody CartRequestDto cartRequestDto){
        cartService.addToCart(cartRequestDto);
        return new BaseResponse(ADD_CART);
    }

    @Operation(summary = "장바구니 조회 API", description = "장바구니 조회 API")
    @GetMapping()
    public DataResponse<List<GetCartListResponseDto>> getCartList(){
        return new DataResponse<>(GET_CART, cartService.getCartList());
    }
    @Operation(summary = "장바구니 수정 API", description = "장바구니 물품 갯수 수정 API")
    @PutMapping()
    public BaseResponse updateCartItem(Long cartId, Integer count){
        cartService.updateCartItem(cartId, count);
        return new BaseResponse(UPDATE_CART);
    }
    @Operation(summary = "장바구니 삭제 API", description = "장바구니 삭제 API")
    @DeleteMapping()
    public BaseResponse deleteCartItem(Long cartId){
        cartService.deleteCartItem(cartId);
        return new BaseResponse(DELETE_CART);
    }
}