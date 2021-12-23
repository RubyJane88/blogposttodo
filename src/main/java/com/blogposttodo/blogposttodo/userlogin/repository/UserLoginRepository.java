package com.blogposttodo.blogposttodo.userlogin.repository;

import com.blogposttodo.blogposttodo.userlogin.entity.UserLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLoginEntity, UUID> {
    @Query(
            "" +
                    "SELECT CASE WHEN COUNT(u) > 0 THEN " +
                    "TRUE ELSE FALSE END " +
                    "FROM UserLoginEntity u " +
                    "WHERE u.email = ?1"
    )
       Boolean selectExistsByEmail(String email);

    //Column(unique = true) is needed in entity class
    UserLoginEntity findByEmail(String email);

}
