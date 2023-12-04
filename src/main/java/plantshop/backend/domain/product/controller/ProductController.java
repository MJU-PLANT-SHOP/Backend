package plantshop.backend.domain.product.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import plantshop.backend.domain.product.dto.response.GetRecommendProductListDto;
import plantshop.backend.domain.product.dto.response.GetProductListForHomepageFirstMenuResponseDto;
import plantshop.backend.domain.product.dto.response.GetProductListForHomepageSecondMenuResponseDto;
import plantshop.backend.domain.product.dto.response.GetProductResponseDto;
import plantshop.backend.domain.product.dto.response.GetProductListResponseDto;
import plantshop.backend.domain.product.entity.Category;
import plantshop.backend.domain.product.service.ProductService;
import plantshop.backend.response.DataResponse;
import plantshop.backend.response.SuccessInfo;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
@Tag(name = "상품 API", description = "상품 API 문서입니다.")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "카테고리 상품 목록 가져오기 API", description = "카테고리 상품 목록 가져오기.")
    @GetMapping("/category")
    public DataResponse<List<GetProductListResponseDto>> getProductList(Category categoryName){
        return new DataResponse<>(SuccessInfo.GET_PRODUCT_LIST, productService.getProductList(categoryName));
    }

    @Operation(summary = "홈페이지(상단 메뉴) 상품 목록 가져오기 API", description = "홈페이지(상단 메뉴) 상품 목록 가져오기.")
    @GetMapping("/homepage/first-menu")
    public DataResponse<List<GetProductListForHomepageFirstMenuResponseDto>> getProductListForHomepageFirstMenu(){
        return new DataResponse<>(SuccessInfo.GET_PRODUCT_LIST, productService.getProductListForHomepageFirstMenu());
    }

    @Operation(summary = "홈페이지(하단 메뉴) 상품 목록 가져오기 API", description = "홈페이지(하단 메뉴) 상품 목록 가져오기.")
    @GetMapping("/homepage/second-menu")
    public DataResponse<List<GetProductListForHomepageSecondMenuResponseDto>> getProductListForHomepageSecondMenu(){
        return new DataResponse<>(SuccessInfo.GET_PRODUCT_LIST, productService.getProductListForHomepageSecondMenu());
    }

    @Operation(summary = "상세 페이지 추천 상품 목록 가져오기 API", description = "상세 페이지 추천 상품 목록 가져오기")
    @GetMapping("/detail/recommend")
    public DataResponse<List<GetRecommendProductListDto>> getRecommendProductList(Long productId){
        return new DataResponse<>(SuccessInfo.GET_PRODUCT_LIST, productService.getRecommendProductList(productId));
    }

    @Operation(summary = "상품 상세정보 가져오기 API", description = "조회할 상품 아이디를 입력해주세요.")
    @GetMapping("/detail")
    public DataResponse<GetProductResponseDto> getProduct(Long productId) {
        return new DataResponse<>(SuccessInfo.GET_PRODUCT, productService.getProduct(productId));
    }
}

