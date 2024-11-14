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
@Table(name = "authors")
@NamedQueries(
        {
                @NamedQuery(name = "Author.findAll", query = "SELECT c FROM Author c")
        }
)
public class Author extends BaseEntity<Long> implements Serializable {
    private String code;

    @Column(name = "author_name")
    private String authorName;

    @ManyToMany(
            mappedBy = "authors",
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    private List<Book> books;
}
