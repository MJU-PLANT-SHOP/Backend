package plantshop.backend.domain.purchase.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plantshop.backend.domain.member.entity.Member;
import plantshop.backend.domain.member.service.MemberService;
import plantshop.backend.domain.product.repository.ProductRepository;
import plantshop.backend.domain.purchase.dto.request.PurchaseRequestDto;
import plantshop.backend.domain.purchase.entity.Purchase;
import plantshop.backend.domain.purchase.repository.PurchaseRepository;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final MemberService memberService;
    private final ProductRepository productRepository;

    @Transactional
    public void tryPurchase(PurchaseRequestDto purchaseRequestDto) {
        Member member = memberService.getCurrentMember();
        Purchase purchase = purchaseRequestDto.toEntity(member, productRepository);
        purchaseRepository.save(purchase);
    }



}
