package plantshop.backend.domain.purchase.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plantshop.backend.domain.member.entity.Member;
import plantshop.backend.domain.member.service.MemberService;
import plantshop.backend.domain.product.entity.Product;
import plantshop.backend.domain.purchase.dto.request.PurchaseDetailRequestDto;
import plantshop.backend.domain.purchase.dto.request.PurchaseRequestDto;
import plantshop.backend.domain.purchase.entity.Purchase;
import plantshop.backend.domain.purchase.entity.PurchaseDetail;
import plantshop.backend.domain.purchase.repository.PurchaseDetailRepository;
import plantshop.backend.domain.purchase.repository.PurchaseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final MemberService memberService;
    private final PurchaseDetailRepository purchaseDetailRepository;
//    private final ProductService productservice;

    @Transactional
    public void tryPurchase(PurchaseRequestDto purchaseRequestDto) {
        Member member = memberService.getCurrentMember();
        Purchase purchase = purchaseRequestDto.toEntity(member);
        purchaseRepository.save(purchase);
        }



}
