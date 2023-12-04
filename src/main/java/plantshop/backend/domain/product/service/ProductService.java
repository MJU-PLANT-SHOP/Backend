package plantshop.backend.domain.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plantshop.backend.domain.product.dto.response.GetRecommendProductListDto;
import plantshop.backend.domain.product.dto.response.GetProductListForHomepageFirstMenuResponseDto;
import plantshop.backend.domain.product.dto.response.GetProductListForHomepageSecondMenuResponseDto;
import plantshop.backend.domain.product.dto.response.GetProductListResponseDto;
import plantshop.backend.domain.product.dto.response.GetProductResponseDto;
import plantshop.backend.domain.product.entity.Category;
import plantshop.backend.domain.product.entity.Product;
import plantshop.backend.domain.product.repository.ProductRepository;
import plantshop.backend.exception.GlobalException;
import plantshop.backend.response.FailureInfo;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    @Transactional
    public List<GetProductListResponseDto> getProductList(Category categoryName) {
        return productRepository.findProductsByCategory(categoryName)
                .stream()
                .map(GetProductListResponseDto::from)
                .collect(Collectors.toList());
    }
    @Transactional
    public GetProductResponseDto getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new GlobalException(FailureInfo.NOT_EXISTENT_PRODUCT));
        return GetProductResponseDto.from(product);
    }
    @Transactional
    public List<GetProductListForHomepageFirstMenuResponseDto> getProductListForHomepageFirstMenu() {
        List<GetProductListForHomepageFirstMenuResponseDto> productList = productRepository.findAll()
                .stream()
                .map(GetProductListForHomepageFirstMenuResponseDto::from)
                .collect(Collectors.toList());
        ArrayList<Integer> selectedNumbers = getIntegerArrayList(productList.size(), 12);
        List<GetProductListForHomepageFirstMenuResponseDto> randomProductList = new ArrayList<>();
        for(int i = 0; i < selectedNumbers.size(); i++) {
            randomProductList.add(productList.get(selectedNumbers.get(i)));
        } return randomProductList;
    }

    @Transactional
    public List<GetProductListForHomepageSecondMenuResponseDto> getProductListForHomepageSecondMenu() {
        List<GetProductListForHomepageSecondMenuResponseDto> productList =  productRepository.findAll()
                .stream()
                .map(GetProductListForHomepageSecondMenuResponseDto::from)
                .collect(Collectors.toList());
        ArrayList<Integer> selectedNumbers = getIntegerArrayList(productList.size(), 12);
        List<GetProductListForHomepageSecondMenuResponseDto> randomProductList = new ArrayList<>();
        for(int i = 0; i < selectedNumbers.size(); i++) {
            randomProductList.add(productList.get(selectedNumbers.get(i)));
        } return randomProductList;
    }
    private static ArrayList<Integer> getIntegerArrayList(int productListLength, int selectNumber) {
        ArrayList<Integer> selectedNumbers = new ArrayList<>();
        Random random = new Random();
        while (selectedNumbers.size() < selectNumber) {
            int randomNumber = random.nextInt(productListLength);
            if (!selectedNumbers.contains(randomNumber))  selectedNumbers.add(randomNumber);
        }
        return selectedNumbers;
    }

    @Transactional
    public List<GetRecommendProductListDto> getRecommendProductList(Long productId) {
        Category category = productRepository.findCategoryByProductId(productId)
                .orElseThrow(() -> new GlobalException(FailureInfo.NOT_EXISTENT_PRODUCT));

        List<GetRecommendProductListDto> categoryProdcutList = productRepository.findProductsByCategoryExceptChoice(category, productId)
                .stream()
                .map(GetRecommendProductListDto::from)
                .collect(Collectors.toList());


        ArrayList<Integer> selectedNumbers = getIntegerArrayList(categoryProdcutList.size(), 5);

        List<GetRecommendProductListDto> randomProductList = new ArrayList<>();
        for (int index : selectedNumbers) {
            if (index >= 0 && index < categoryProdcutList.size())  randomProductList.add(categoryProdcutList.get(index));
        } return randomProductList;

    }
}
