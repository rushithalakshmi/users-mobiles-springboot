package com.fastpixlearning.userexample.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.fastpixlearning.userexample.entities.Mobile;



import java.util.UUID;


@Repository
public interface MobileRepository extends CrudRepository<Mobile, UUID> {


}
