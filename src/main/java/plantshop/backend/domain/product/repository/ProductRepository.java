package plantshop.backend.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import plantshop.backend.domain.product.dto.response.GetProductListResponseDto;
import plantshop.backend.domain.product.entity.Category;
import plantshop.backend.domain.product.entity.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findProductsByCategory(@Param("category") Category category);

    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.id != :id")
    List<Product> findProductsByCategoryExceptChoice(@Param("category") Category category, @Param("id") Long id);

    @Query("SELECT p.category FROM Product p WHERE p.id = :productId")
    Optional<Category> findCategoryByProductId(@Param("productId") Long productId);

    @Query("SELECT p.price FROM Product p WHERE p.id = :productId")
    Integer findPriceById(@Param("productId") Long productId);

}
