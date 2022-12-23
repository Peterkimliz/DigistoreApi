package com.example.digishopapi.repository;


import com.example.digishopapi.models.Rider;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends MongoRepository<Rider,String> {
}
