package com.example.digishopapi.repository;


import com.example.digishopapi.models.Rider;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RiderRepository extends MongoRepository<Rider,String> {
    Optional<Rider>findByEmail(String email);
    List<Rider> findByShopId(String shopId, Pageable pageable);

}
