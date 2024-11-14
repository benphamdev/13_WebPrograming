package org.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ratings")
@NamedQueries(
        {
                @NamedQuery(name = "Rating.findAll", query = "SELECT c FROM Rating c")
        }
)
public class Rating extends BaseEntity<Long> implements Serializable {
    @Column(name = "review_text")
    private String reviewText;

    private String rating;

    @ManyToOne
    private Book book;

    @ManyToOne
    private User user;
}
