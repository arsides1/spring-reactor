package com.reactor.dto;

import lombok.Data;

@Data
public class ProductSaveDTO {
    private String name;
    private String description;
    private Integer amount;
}
