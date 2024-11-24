package org.com.entity;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Video_22162006 implements Serializable {
    private Long id;
    private String title;
    private String poster;
    private int views;
    private String description;
    private boolean isActive;

    private int categoryId;
    private List<Favorite_22162006> favorite22162006s;
    private List<Share_22162006> share22162006s;
}
