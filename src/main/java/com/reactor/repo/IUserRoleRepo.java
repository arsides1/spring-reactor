package com.reactor.repo;


import com.reactor.model.UserRole;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IUserRoleRepo extends ReactiveCrudRepository<UserRole, Long> {

    Flux<UserRole> findByIdUser(Long idUser);
}