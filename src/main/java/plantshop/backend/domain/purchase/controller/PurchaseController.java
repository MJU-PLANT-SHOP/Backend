package plantshop.backend.domain.purchase.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plantshop.backend.domain.purchase.dto.request.PurchaseDetailRequestDto;
import plantshop.backend.domain.purchase.dto.request.PurchaseRequestDto;
import plantshop.backend.domain.purchase.service.PurchaseDetailService;
import plantshop.backend.domain.purchase.service.PurchaseService;
import plantshop.backend.response.BaseResponse;
import plantshop.backend.response.SuccessInfo;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/purchase")
@Tag(name = "주문 API", description = "주문 API 문서입니다.")
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final PurchaseDetailService purchaseDetailService;

    @Operation(summary = "결제하기 API", description = "내용 입력")
    @PostMapping("/trypurchase")
    public BaseResponse tryPurchase(@RequestBody PurchaseRequestDto purchaseRequestDto){
        purchaseService.tryPurchase(purchaseRequestDto);
        purchaseDetailService.tryPurchase(purchaseRequestDto);
        return new BaseResponse(SuccessInfo.TRY_PURCHASE);
    }

}
