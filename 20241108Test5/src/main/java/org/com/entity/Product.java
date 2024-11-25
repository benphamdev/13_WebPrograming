package org.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@NamedQueries(
        {
                @NamedQuery(name = "Product.findAll", query = "SELECT u FROM Product u")
        }
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Double price;

    private String imageLink;

    private Integer amount;

    private int nVisit; // Lượt xem

    private int nSold;  // Số lượng đã bán

    private boolean active;

    private int availableQuantity; // Số lượng còn lại

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "sellerId", nullable = false)
    private User seller;

    @OneToMany(mappedBy = "product")
    private List<Comment> comments;
}
