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
public class Author implements Serializable {
    private Long id;
    private String authorName;
    private Date dateOfBirth;
    private List<Book> books;  // Quan hệ nhiều-nhiều với Book
}
