package plantshop.backend.domain.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

import static jakarta.persistence.CascadeType.ALL;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Size size;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Integer price;
    @OneToMany(mappedBy = "product", cascade = ALL, orphanRemoval = true)
    private ArrayList<ProductImage> productImageList = new ArrayList<>();
}
