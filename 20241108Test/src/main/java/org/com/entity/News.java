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
@Table(name = "news")
@NamedQueries(
        {
                @NamedQuery(name = "News.findAll", query = "SELECT n FROM News n")
        }
)
public class News extends BaseEntity<Long> implements Serializable {
    @Column(name = "title")
    private String title;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "content")
    private String content;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "news")
    private List<Comment> comments;
}
