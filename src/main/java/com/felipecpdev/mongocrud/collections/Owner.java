package com.felipecpdev.mongocrud.collections;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "owners")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Owner {

    @Id
    private String id;
    private String name;
    private String email;
    private List<String> phone;
    private List<Car> carsList;
}
