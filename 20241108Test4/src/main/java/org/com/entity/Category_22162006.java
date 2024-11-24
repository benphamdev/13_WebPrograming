package org.com.entity;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category_22162006 implements Serializable {
    private int id;
    private String categoryName;
    private String code;
    private byte[] images;
    private boolean status;

    private List<Video_22162006> video22162006s;
}
