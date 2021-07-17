package com.shop.egs.repository;

import com.shop.egs.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    Boolean existsByNameAndUserName(String name, String userName);
    Optional<User> findByUserName(String userName);
    Optional<User> findByUserNameAndNameAndCode(String userName,String name,String code);
    @Query("SELECT us.id FROM User AS us WHERE us.userName=:username and us.name=:name and us.code=:code")
    Long getUserIdByUserNameAndNameAndCode(@Param("username") String userName,@Param("name")  String name,@Param("code")  String code);

}
