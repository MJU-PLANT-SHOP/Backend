package plantshop.backend.domain.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plantshop.backend.domain.cart.dto.request.CartRequestDto;
import plantshop.backend.domain.cart.dto.response.GetCartListResponseDto;
import plantshop.backend.domain.cart.entity.Cart;
import plantshop.backend.domain.cart.repository.CartRepository;
import plantshop.backend.domain.member.entity.Member;
import plantshop.backend.domain.member.service.MemberService;
import plantshop.backend.domain.product.entity.Product;
import plantshop.backend.domain.product.repository.ProductRepository;
import plantshop.backend.exception.GlobalException;
import plantshop.backend.response.FailureInfo;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final MemberService memberService;
    private final ProductRepository productRepository;

    public void addToCart(CartRequestDto cartRequestDto) {
        Member member = memberService.getCurrentMember();
        Product product = productRepository.findById(cartRequestDto.getProductId())
                .orElseThrow(()-> new GlobalException(FailureInfo.NOT_EXISTENT_PRODUCT));
        //member, product에 해당하는 객체가 저장되어 있는지 확인하기(있으면 count 업데이트)
        cartRepository.save(cartRequestDto.toEntity(member, product));
    }

    public List<GetCartListResponseDto> getCartList() {
        Member member = memberService.getCurrentMember();
        return cartRepository.findAllByMemberId(member.getId())
                .stream()
                .map(GetCartListResponseDto::from)
                .collect(Collectors.toList());
    }

    public void updateCartItem(Long cartId, Integer count) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new GlobalException(FailureInfo.NOT_EXISTNET_CARTITEM));
        cart.updateCount(count);
    }

    public void deleteCartItem(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new GlobalException(FailureInfo.NOT_EXISTENT_CART_ITEM));
        cartRepository.delete(cart);
    }
}