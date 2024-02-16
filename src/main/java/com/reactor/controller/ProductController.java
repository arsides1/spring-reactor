package com.reactor.controller;

import com.reactor.dto.ProductResponseDTO;
import com.reactor.dto.ProductSaveDTO;
import com.reactor.model.Product;
import com.reactor.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/Product")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService service;

    @GetMapping
    public Mono<ResponseEntity<Flux<Product>>> findAll() {
        Flux<Product> fx = service.findAll();

        return Mono.just(ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fx)
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @GetMapping("/{id}")
    public Mono<ResponseEntity<Product>> findById(@PathVariable("id") Integer id) {
        return service.findById(id) //Mono<Dish>
                .map(e -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e)
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

   /* @PostMapping
    public Mono<ResponseEntity<Product>> save(@RequestBody Product product, final ServerHttpRequest req) {
        return service.save(product)
                .map(savedProduct -> ResponseEntity
                        .created(URI.create(req.getURI().toString().concat("/").concat(String.valueOf(savedProduct.getId()))))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(savedProduct))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }*/

    @PostMapping
    public Mono<ResponseEntity<ProductResponseDTO>> save(@RequestBody ProductSaveDTO productSaveDTO, final ServerHttpRequest req) {
        return service.save(productSaveDTO)
                .map(savedProduct -> ResponseEntity
                        .created(URI.create(req.getURI().toString().concat("/").concat(savedProduct.getId().toString())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(savedProduct))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

   @PutMapping("/{id}")
    public Mono<ResponseEntity<Product>> update(@PathVariable Integer id, @RequestBody Product productDetails, final ServerHttpRequest req) {
        return service.update(id, productDetails)
                .map(updatedProduct -> ResponseEntity
                        .ok() // Indica que la actualización fue exitosa.
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(updatedProduct))
                .defaultIfEmpty(ResponseEntity.notFound().build()); // Si el producto con ese ID no existe.
    }


    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Map<String, String>>> delete(@PathVariable("id") Integer id) {
        return service.delete(id)
                .thenReturn(ResponseEntity.ok().body(Collections.singletonMap("message", "Producto eliminado")))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
