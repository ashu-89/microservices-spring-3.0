package com.ashu.microservices.product.repository;

import com.ashu.microservices.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
//public interface ProductRepository extends CrudRepository<Product, String> {

}
