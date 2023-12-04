package com.felipecpdev.mongocrud.repositories;

import com.felipecpdev.mongocrud.collections.Owner;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Repository
public interface OwnerRepository extends MongoRepository<Owner, String> {

    List<Owner> findByNameStartsWith(String name);

    List<Owner> findByAgeBetween(Integer minAge, Integer maxAge);

    @Query(value = "{ age : { $gte: ?0, $lte: ?1  } }",
            fields = "{ phones: 0 }")
    List<Owner> findPersonByAgeBetween(Integer minAge, Integer maxAge);

}
