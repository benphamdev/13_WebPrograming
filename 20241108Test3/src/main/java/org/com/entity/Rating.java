package org.com.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rating implements Serializable {
    private Long id;
    private Long userId;
    private Long bookId;
    private int rating;
    private String reviewText;
}
