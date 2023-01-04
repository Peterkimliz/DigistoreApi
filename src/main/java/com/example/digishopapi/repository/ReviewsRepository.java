package com.example.digishopapi.repository;
import com.example.digishopapi.models.Reviews;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends MongoRepository<Reviews,String> {
    Reviews findByShopIdAndUserId(String shopId,String userId);

    List<Reviews> findByShopId(String shopId);
}
