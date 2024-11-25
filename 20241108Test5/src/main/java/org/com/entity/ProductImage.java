package org.com.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_image")
@NamedQueries(
        {
                @NamedQuery(name = "ProductImage.findAll", query = "SELECT u FROM ProductImage u")
        }
)
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idProduct", nullable = false)
    private Product product;

    private String imageLink;
}
