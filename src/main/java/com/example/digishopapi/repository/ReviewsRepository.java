package com.example.digishopapi.repository;
import com.example.digishopapi.models.Reviews;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsRepository extends MongoRepository<Reviews,String> {
}
