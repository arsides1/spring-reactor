package com.reactor.dto;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private Integer id;
    private String name;
    private String description;
    private Integer amount;
}