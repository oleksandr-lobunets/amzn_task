package com.agencyamazon.repository.impl;

import com.agencyamazon.domain.dto.AsinsSummary;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import com.agencyamazon.domain.model.report.sales.SalesAndTrafficByAsin;
import com.agencyamazon.repository.SalesAndTrafficByAsinRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
@AllArgsConstructor
public class SalesAndTrafficByAsinRepositoryImpl implements SalesAndTrafficByAsinRepository {
    private final MongoTemplate mongoTemplate;

    private static final String COLLECTION_NAME = "salesAndTrafficByAsin";
    @Override
    public List<SalesAndTrafficByAsin> findAllByParentAsinIn(List<String> asins) {
        if (asins == null || asins.isEmpty()) {
            return List.of();
        }

        Query query = new Query(Criteria.where("parentAsin").in(asins));
        return mongoTemplate.find(query, SalesAndTrafficByAsin.class);
    }

    @Override
    public SalesAndTrafficByAsin save(SalesAndTrafficByAsin entity) {
        return mongoTemplate.save(entity, COLLECTION_NAME);
    }

    @Override
    public Collection<SalesAndTrafficByAsin> saveAll(List<SalesAndTrafficByAsin> entities) {
        return mongoTemplate.insert(entities, COLLECTION_NAME);
    }

    @Override
    public AsinsSummary getAggregatedStats() {
        AggregationResults<AsinsSummary> result = mongoTemplate.aggregate(
                Aggregation.newAggregation(
                        SalesAndTrafficByAsin.class,
                        Aggregation.group()
                                .sum("salesByAsin.unitsOrdered").as("unitsOrdered")
                                .sum("salesByAsin.unitsOrderedB2B").as("unitsOrderedB2B")
                                .sum("salesByAsin.orderedProductSales.amount").as("orderedProductSales")
                                .sum("salesByAsin.orderedProductSalesB2B.amount").as("orderedProductSalesB2B")
                                .sum("salesByAsin.totalOrderItems").as("totalOrderItems")
                                .sum("salesByAsin.totalOrderItemsB2B").as("totalOrderItemsB2B")
                        // here could be more fields, but that's enough for the task
                ), AsinsSummary.class);
        return result.getUniqueMappedResult();

    }

    @Override
    public void deleteAll(List<String> ids) {
        Query query = new Query(Criteria.where("id").in(ids));
        mongoTemplate.remove(query, SalesAndTrafficByAsin.class);
    }


}
