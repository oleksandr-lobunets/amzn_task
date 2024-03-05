package com.agencyamazon.repository;

import com.agencyamazon.report.traffic.SalesAndTrafficByAsin;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SalesAndTrafficByAsinRepository extends MongoRepository<SalesAndTrafficByAsin, String> {
    Optional<SalesAndTrafficByAsin> findByParentAsin(String parentAsin);

//    Collection<SalesAndTrafficByAsin> findByParentAsins(List<String> asins);
}
