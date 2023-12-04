package com.felipecpdev.mongocrud.services;

import com.felipecpdev.mongocrud.collections.Owner;

import java.util.List;

public interface OwnerService {
    String save(Owner owner);

    List<Owner> getOwnerStartWith(String name);
}
