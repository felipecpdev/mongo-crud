package com.felipecpdev.mongocrud.controllers;

import com.felipecpdev.mongocrud.collections.Owner;
import com.felipecpdev.mongocrud.services.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    public String save(@RequestBody Owner owner){
        return  ownerService.save(owner);
    }

    @GetMapping("/start-with")
    public List<Owner> getOwnerStartWith(@RequestParam("name") String name){
        return ownerService.getOwnerStartWith(name);
    }

}
