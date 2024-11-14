package org.com.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {
    private Long id;
    private String isbn;
    private String title;
    private String publisher;
    private double price;
    private String description;
    private Date publishDate;
    private String coverImage;
    private Integer quantity;
    private List<Author> authors;  // Quan hệ nhiều-nhiều với Author
    private List<Rating> ratings;  // Quan hệ một-nhiều với Rating


}
