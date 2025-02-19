package com.ashu.microservices.product.repository;

import com.ashu.microservices.product.model.Product;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Observed
public interface ProductRepository extends MongoRepository<Product, String> {
//public interface ProductRepository extends CrudRepository<Product, String> {

}
