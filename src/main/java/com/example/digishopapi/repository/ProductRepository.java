package com.example.digishopapi.repository;

import com.example.digishopapi.models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
    List<Product> findByShopId(String shopId,Sort sort);

}
