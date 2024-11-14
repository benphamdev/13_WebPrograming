package org.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
@NamedQueries(
        {
                @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
        }
)
public class Category extends BaseEntity<Long> implements Serializable {
    private String code;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<News> news;
}
