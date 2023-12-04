package com.felipecpdev.mongocrud.controllers;

import com.felipecpdev.mongocrud.collections.Owner;
import com.felipecpdev.mongocrud.services.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    @Operation(
            summary = "save owner",
            description = "save owner with mongodb")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    public String save(@RequestBody Owner owner) {
        return ownerService.save(owner);
    }

    @GetMapping("/start-with")
    @Operation(
            summary = "start-with owner",
            description = "start with name")
    public List<Owner> getOwnerStartWith(@RequestParam("name") String name) {
        return ownerService.getOwnerStartWith(name);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        ownerService.delete(id);
    }

    @GetMapping("/age")
    public List<Owner> getOwnerByAge(
            @RequestParam Integer minAge,
            @RequestParam Integer maxAge
    ) {
        return ownerService.getOwnerByAge(minAge, maxAge);
    }

    @GetMapping("/search")
    Page<Owner> searchOwner(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(required = false) String modelName,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ownerService.search(name, minAge, maxAge, modelName, pageable);
    }


    @GetMapping("/oldest")
    @Operation(summary = "getOldestOwnerByModelName",
            description = "Oldest Owner By Model CAR Name")
    public List<Document> getOldestOwnerByModelName() {
        return ownerService.getOldestOwnerByModelName();
    }


}
