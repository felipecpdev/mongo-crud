package com.felipecpdev.mongocrud.collections;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class Car {
    private String modelName;
    private Long modelYear;
    private LocalDateTime datePurchased;
}
