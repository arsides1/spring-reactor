package com.reactor.repo;

import com.reactor.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IProductRepo extends ReactiveCrudRepository<Product, Integer> {
}
