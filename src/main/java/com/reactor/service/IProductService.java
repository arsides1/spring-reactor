package com.reactor.service;

import com.reactor.dto.ProductResponseDTO;
import com.reactor.dto.ProductSaveDTO;
import com.reactor.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {
    //Mono<Product> save(Product product);
    Mono<Product> update(Integer id, Product productDetails);
    Flux<Product> findAll();
    Mono<Product>findById(Integer id);

    Mono<Void> delete(Integer id);
    Mono<ProductResponseDTO> save(ProductSaveDTO productSaveDTO);
}
