package com.felipecpdev.mongocrud.services;

import com.felipecpdev.mongocrud.collections.Owner;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OwnerService {
    String save(Owner owner);

    List<Owner> getOwnerStartWith(String name);

    void delete(String id);

    List<Owner> getOwnerByAge(Integer minAge, Integer maxAge);

    Page<Owner> search(String name, Integer minAge, Integer maxAge, String modelName, Pageable pageable);

    List<Document> getOldestOwnerByModelName();

    List<Document> getNumberOfCarsByOwners();
}
