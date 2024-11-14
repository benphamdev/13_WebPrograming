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
@Table(name = "comments")
@NamedQueries(
        {
                @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c")
        }
)
public class Comment extends BaseEntity<Long> implements Serializable {
    private String content;
    private String author;
    private int status;

    @ManyToOne
    private News news;

    @ManyToOne
    private User user;
}
