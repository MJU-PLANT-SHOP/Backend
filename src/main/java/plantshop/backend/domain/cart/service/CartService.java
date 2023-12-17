package plantshop.backend.domain.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plantshop.backend.domain.cart.dto.request.CartAddRequestDto;
import plantshop.backend.domain.cart.dto.request.CartUpdateRequestDto;
import plantshop.backend.domain.cart.dto.response.GetCartListResponseDto;
import plantshop.backend.domain.cart.entity.Cart;
import plantshop.backend.domain.cart.repository.CartRepository;
import plantshop.backend.domain.member.entity.Member;
import plantshop.backend.domain.member.repository.MemberRepository;
import plantshop.backend.domain.member.service.MemberService;
import plantshop.backend.domain.product.entity.Product;
import plantshop.backend.domain.product.repository.ProductRepository;
import plantshop.backend.exception.GlobalException;
import plantshop.backend.response.FailureInfo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final MemberService memberService;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    public void addToCart(CartAddRequestDto cartRequestDto) {
        Member member = memberService.getCurrentMember();
        Product product = productRepository.findById(cartRequestDto.getProductId())
                .orElseThrow(()-> new GlobalException(FailureInfo.NOT_EXISTENT_PRODUCT));
        Optional<Cart> opotionalCart = cartRepository.findByMemberIdAndProductId(member.getId(), product.getId());
        if(!opotionalCart.isEmpty()){
            Cart cart = opotionalCart.get();
            cart.plusCount(cartRequestDto.getCount());
        } else cartRepository.save(cartRequestDto.toEntity(member, product));
    }
    public List<GetCartListResponseDto> getCartList() {
        Member member = memberService.getCurrentMember();
        return cartRepository.findAllByMemberId(member.getId())
                .stream()
                .map(GetCartListResponseDto::from)
                .collect(Collectors.toList());
    }
    public void updateCartItem(CartUpdateRequestDto cartUpdateRequestDto) {
        Optional<Cart> opotionalCart = findCartByProductId(cartUpdateRequestDto.getProductId());
        if(!opotionalCart.isEmpty()){
            Cart cart = opotionalCart.get();
            cart.updateCount(cartUpdateRequestDto.getCount());
        } else throw new GlobalException(FailureInfo.NOT_EXISTENT_CART_ITEM);
    }
    public void deleteCartItem(Long productId) {
        Optional<Cart> opotionalCart = findCartByProductId(productId);
        if(!opotionalCart.isEmpty()){
            Cart cart = opotionalCart.get();
            cartRepository.delete(cart);
        } else throw new GlobalException(FailureInfo.NOT_EXISTENT_CART_ITEM);
    }
    private Optional<Cart> findCartByProductId(Long productId) {
        Member member = memberService.getCurrentMember();
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new GlobalException(FailureInfo.NOT_EXISTENT_PRODUCT));
        return cartRepository.findByMemberIdAndProductId(member.getId(), productId);
    }
}