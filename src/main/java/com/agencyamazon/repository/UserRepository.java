package com.agencyamazon.repository;

import com.agencyamazon.entity.traffic.SalesAndTrafficByAsin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SalesAndTrafficByAsinRepository extends MongoRepository<SalesAndTrafficByAsin, String> {
    Optional<SalesAndTrafficByAsin> findByParentAsin(String parentAsin);

}
