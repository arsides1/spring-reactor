package com.reactor.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("usuario_rol")
public class UserRole {

    @NotNull
    @Column("idUsuario")
    private Long idUser;

    @NotNull
    @Column("idRol")
    private Long idRole;
}