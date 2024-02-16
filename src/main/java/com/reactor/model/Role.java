package com.reactor.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("rol")
public class Role {

    @Id
    @Column("idRol")
    private Long idRole;

    @NotEmpty
    @Column("nombre")
    private String name;
}
