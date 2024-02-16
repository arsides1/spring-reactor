package com.reactor.service.impl;

import com.reactor.model.Role;
import com.reactor.model.User;
import com.reactor.model.UserRole;
import com.reactor.repo.IRoleRepo;
import com.reactor.repo.IUserRepo;
import com.reactor.repo.IUserRoleRepo;
import com.reactor.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserRepo repo;
    private final IRoleRepo rolRepo;
    private final BCryptPasswordEncoder bcrypt;

    private final IUserRoleRepo userRoleRepo;
    @Override
    public Mono<User> save(User user) {
        return repo.save(user);
    }

    @Override
    public Mono<User> saveHash(User user) {
        user.setPassword(bcrypt.encode(user.getPassword()));
        return repo.save(user);
    }

    @Override
    public Mono<com.reactor.security.User> searchByUser(String username) {
        // Paso 1: Buscar el usuario por username.
        return repo.findByUsername(username)
                .flatMap(user ->
                        // Paso 2: Utilizar el id del usuario para buscar todos los UserRole asociados.
                        userRoleRepo.findByIdUser(user.getIdUser())
                                .flatMap(userRole ->
                                        // Paso 3: Para cada UserRole, buscar el Role correspondiente.
                                        rolRepo.findById(userRole.getIdRole())
                                )
                                .map(role -> role.getName()) // Extraer el nombre de cada Role.
                                .collectList() // Colectar los nombres de roles en una lista.
                                .flatMap(rolesNames -> {
                                    // Paso 5: Crea una instancia de com.reactor.security.User con la lista de nombres de roles.
                                    return Mono.just(new com.reactor.security.User(user.getUsername(), user.getPassword(), user.isStatus(), rolesNames));
                                })
                );
    }


}
