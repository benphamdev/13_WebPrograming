package org.com.service;

import org.com.entity.Rating;

import java.util.List;

public interface IRatingService {
    void save(Rating rating);

    List<Rating> findAllWithUser();

    List<Rating> findAllWithBookId(long bookId);
}