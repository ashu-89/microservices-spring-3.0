package com.ashu.microservices.product.controller;

import com.ashu.microservices.product.dto.ProductRequestDTO;
import com.ashu.microservices.product.dto.ProductResponseDTO;
import com.ashu.microservices.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO requestDTO){
        return productService.createProduct(requestDTO);
    }

}
