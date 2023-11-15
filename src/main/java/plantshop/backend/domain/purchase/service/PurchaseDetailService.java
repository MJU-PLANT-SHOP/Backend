package plantshop.backend.domain.purchase.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plantshop.backend.domain.member.entity.Member;
import plantshop.backend.domain.member.service.MemberService;
import plantshop.backend.domain.purchase.dto.request.PurchaseDetailRequestDto;
import plantshop.backend.domain.purchase.dto.request.PurchaseRequestDto;
import plantshop.backend.domain.purchase.entity.Purchase;
import plantshop.backend.domain.purchase.entity.PurchaseDetail;
import plantshop.backend.domain.purchase.repository.PurchaseDetailRepository;

@Service
@RequiredArgsConstructor
public class PurchaseDetailService {
    private final PurchaseDetailRepository purchaseDetailRepository;
    private  MemberService memberService;
    private  PurchaseDetailRequestDto purchaseDetailRequestDto;

    @Transactional
    public void tryPurchase(PurchaseRequestDto purchaseRequestDto) {
        Member member = memberService.getCurrentMember();
        Purchase purchase = purchaseRequestDto.toEntity(member);
        PurchaseDetail purchaseDetail = purchaseDetailRequestDto.toEntity(purchase);
        purchaseDetailRepository.save(purchaseDetail);
    }
}
