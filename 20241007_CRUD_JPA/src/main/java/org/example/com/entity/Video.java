package org.example.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "videos")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v")
public class Video implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "VideoId")
    private String videoId;
    @Column(name = "Active")
    private int active;
    @Column(name = "Description", length = 500, nullable = true)
    private String description;
    @Column(name = "Poster", length = 500, nullable = true)
    private String poster;
    @Column(name = "Title", length = 500, nullable = true)
    private String title;
    @Column(name = "Views")
    private int views;

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;
}
