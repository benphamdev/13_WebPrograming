package org.com.service;

import org.com.entity.Cart;

import java.util.List;

public interface ICartService {
    void save(Cart share);

    List<Cart> findAllWithUser();

    List<Cart> findAllWithVideoId(long bookId);
}