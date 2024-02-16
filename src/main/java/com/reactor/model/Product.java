package com.reactor.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("product")
public class Product {

    @Id
    @Column("idProduct")
    private Integer id;

    @NotEmpty
    @Column("nombre")
    private String name;

    @NotEmpty
    @Column("descripcion")
    private String description;

    @NotNull
    @Column("fechaRegis")
    private LocalDate RegisterDate;

    @Column("cantidad")
    private Integer amount;
}
