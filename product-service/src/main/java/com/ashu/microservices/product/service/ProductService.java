package com.ashu.microservices.product.service;

import com.ashu.microservices.product.dto.ProductRequestDTO;
import com.ashu.microservices.product.dto.ProductResponseDTO;
import com.ashu.microservices.product.model.Product;
import com.ashu.microservices.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@Slf4j
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {
        Product product = Product
                .builder()
                .name(requestDTO.name())
                .description(requestDTO.description())
                .price(requestDTO.price())
                .build();

//        Product product = new Product();
//        product.setName(requestDTO.name());
//        product.setDescription(requestDTO.description());
//        product.setPrice(requestDTO.price());

        Product savedProduct = productRepository.save(product);

        log.info("product with id {} created successfully", savedProduct.getId());

        return  new ProductResponseDTO(savedProduct.getId(), savedProduct.getName(),
                        savedProduct.getDescription(), savedProduct.getPrice());



    }
}
