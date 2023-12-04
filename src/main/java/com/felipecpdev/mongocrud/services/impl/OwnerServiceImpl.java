package com.felipecpdev.mongocrud.services.impl;

import com.felipecpdev.mongocrud.collections.Owner;
import com.felipecpdev.mongocrud.repositories.OwnerRepository;
import com.felipecpdev.mongocrud.services.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    @Override
    public String save(Owner owner) {
        return ownerRepository.save(owner).getId();
    }

    @Override
    public List<Owner> getOwnerStartWith(String name) {
        return ownerRepository.findByNameStartsWith(name);
    }
}
