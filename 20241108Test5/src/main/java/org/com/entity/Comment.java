package org.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
@NamedQueries(
        {
                @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c")
        }
)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "idProduct", nullable = false)
    private Product product;

    private String comment;
    private int rating; // Được dùng để đánh giá (số lượng likes)
    private LocalDateTime time;
}
