package org.com.test4.repository;

import org.com.test4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    /**
     * findByName method is used to retrieve a user by their username.
     * It returns an Optional of UserInfo, which will be empty if no user is found.
     */
    Optional<User> findByUserName(String userName);
}
