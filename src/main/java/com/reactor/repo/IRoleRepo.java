package com.reactor.repo;



import com.reactor.model.Role;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface IRoleRepo extends ReactiveCrudRepository<Role, Long> {


}
