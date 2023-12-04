package com.felipecpdev.mongocrud.services.impl;

import com.felipecpdev.mongocrud.collections.Owner;
import com.felipecpdev.mongocrud.repositories.OwnerRepository;
import com.felipecpdev.mongocrud.services.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    private final MongoTemplate mongoTemplate;

    @Override
    public String save(Owner owner) {
        return ownerRepository.save(owner).getId();
    }

    @Override
    public List<Owner> getOwnerStartWith(String name) {
        return ownerRepository.findByNameStartsWith(name);
    }

    @Override
    public void delete(String id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public List<Owner> getOwnerByAge(Integer minAge, Integer maxAge) {
        return ownerRepository.findPersonByAgeBetween(minAge, maxAge);
    }

    @Override
    public Page<Owner> search(String name, Integer minAge, Integer maxAge, String modelName, Pageable pageable) {
        Query query = new Query().with(pageable);
        List<Criteria> criteria = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            criteria.add(Criteria.where("name")
                    .regex(name, "i"));
        }

        if (minAge != null && maxAge != null) {
            criteria.add(Criteria.where("age")
                    .gte(minAge).lte(maxAge));
        }
        if (modelName != null && !modelName.isEmpty()) {
            criteria.add(Criteria.where("carsList.modelName")
                    .is(modelName));
        }

        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria()
                    .andOperator(criteria.toArray(new Criteria[0])));
        }

        return PageableExecutionUtils.getPage(
                mongoTemplate.find(query, Owner.class),
                pageable,
                () -> mongoTemplate
                        .count(query.skip(0).limit(0), Owner.class));
    }
}
