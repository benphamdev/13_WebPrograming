package org.com.service;

import org.com.entity.Share_22162006;

import java.util.List;

public interface IShareService_22162006 {
    void save(Share_22162006 share22162006);

    List<Share_22162006> findAllWithUser();

    List<Share_22162006> findAllWithVideoId(long bookId);
}