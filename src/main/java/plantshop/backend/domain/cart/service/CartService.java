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
    private final ProductService productService;

    public void addToCart(CartRequestDto cartRequestDto) {
        Member member = memberService.getCurrentMember();
        Product product = productService.getCurrentProduct();
        cartRepository.save(cartRequestDto.toEntity(member, product));
    }

    public List<GetCartListResponseDto> getCartList() {
        return cartRepository.findAll()
                .stream()
                .map(GetCartListResponseDto::from)
                .collect(Collectors.toList());
    }

    public void updateCartItem(Long cartId, Integer count) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new GlobalException(FailureInfo.NOT_EXISTNET_CARTITEM));
        cartRepository.delete(cart);
        cart.updateCount(count);
        cartRepository.save(cart);
    }

    public void deleteCartItem(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new GlobalException(FailureInfo.NOT_EXISTNET_CARTITEM));
        cartRepository.delete(cart);
    }
}