package com.reactor.service.impl;

import com.reactor.dto.ProductResponseDTO;
import com.reactor.dto.ProductSaveDTO;
import com.reactor.model.Product;
import com.reactor.repo.IProductRepo;
import com.reactor.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IProductRepo repo;
   /* @Override
    public Mono<Product> save(Product product) {
        return repo.save(product);
    }*/

    @Override
    public Mono<Product> update(Integer id, Product productDetails) {
        return repo.findById(id)
                .flatMap(existingProduct -> {
                    Optional.ofNullable(productDetails.getName())
                            .filter(name -> !name.isEmpty())
                            .ifPresent(existingProduct::setName);

                    Optional.ofNullable(productDetails.getDescription())
                            .filter(description -> !description.isEmpty())
                            .ifPresent(existingProduct::setDescription);

                    Optional.ofNullable(productDetails.getRegisterDate())
                            .ifPresent(existingProduct::setRegisterDate);

                    Optional.ofNullable(productDetails.getAmount())
                            .ifPresent(existingProduct::setAmount);

                    // Guarda el producto actualizado
                    return repo.save(existingProduct);
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "id no encontrado"))); // Maneja el caso donde el ID no se encuentra
    }


    @Override
    public Flux<Product> findAll() {
        return repo.findAll();
    }

    @Override
    public Mono<Product> findById(Integer id) {
        return repo.findById(id);
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return repo.deleteById(id);
    }

    @Override
    public Mono<ProductResponseDTO> save(ProductSaveDTO productSaveDTO) {
        // Convertir DTO a entidad
        Product product = new Product();
        product.setName(productSaveDTO.getName());
        product.setDescription(productSaveDTO.getDescription());
        product.setAmount(productSaveDTO.getAmount());
        // La fecha se gestiona automáticamente, así que no se establece aquí

        return repo.save(product)
                .map(this::convertToDto);
    }

    private ProductResponseDTO convertToDto(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setAmount(product.getAmount());
        // No incluir la fecha
        return dto;
    }
}
