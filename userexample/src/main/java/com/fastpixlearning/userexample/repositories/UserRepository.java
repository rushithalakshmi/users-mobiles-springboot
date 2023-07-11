package com.fastpixlearning.userexample.repositories;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.fastpixlearning.userexample.entities.User;

import java.util.UUID;
import java.util.*;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    @Query("SELECT * FROM users u WHERE u.name LIKE CONCAT('%', :name, '%')" +
            "LIMIT :limit OFFSET :offset")
    List<User> findAllByNameContaining(@Param("name") String name,
                                       @Param("limit") int limit,
                                       @Param("offset") int offset);


    //List<User> findAllByNameContaining(String name);

}
