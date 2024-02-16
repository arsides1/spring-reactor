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
@Table("usuario")
public class User {

    @Id
    @Column("idUsuario")
    private Long idUser;

    @NotEmpty
    @Column("usuario")
    private String username;

    @NotEmpty
    @Column("clave")
    private String password;

    @NotEmpty
    @Column("estado")
    private boolean status;
}
