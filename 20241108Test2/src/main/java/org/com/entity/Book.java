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
@Table(name = "books")
@NamedQueries(
        {
                @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b")
        }
)
public class Book extends BaseEntity<Long> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(unique = true)
    private String isbn;

    private String title;

    private String publisher;

    private double price;

    private String description;

    @Column(name = "cover_image")
    private String coverImage;

    private Integer quantity;

    @ManyToMany(
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    @OneToMany(mappedBy = "book")
    private List<Rating> ratings;
}
