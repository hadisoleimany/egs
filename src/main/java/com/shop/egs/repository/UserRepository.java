package com.shop.egs.repository;

import com.shop.egs.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    Boolean existsByNameAndUserName(String name, String userName);
    Optional<User> findByUserName(String userName);
}
