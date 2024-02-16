package com.reactor.service;

import com.reactor.model.User;
import reactor.core.publisher.Mono;

public interface IUserService {
    Mono<User> save(User user);

    Mono<User> saveHash(User user); // Método específico para guardar un hash, por ejemplo

    Mono<com.reactor.security.User> searchByUser(String username);
}
