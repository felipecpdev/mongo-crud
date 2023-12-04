package com.felipecpdev.mongocrud.repositories;

import com.felipecpdev.mongocrud.collections.Owner;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends MongoRepository<Owner, String> {

    List<Owner> findByNameStartsWith(String name);

}
