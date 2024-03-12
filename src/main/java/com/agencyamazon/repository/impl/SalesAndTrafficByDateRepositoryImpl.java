package com.agencyamazon.repository.impl;

import com.agencyamazon.domain.model.report.sales.SalesAndTrafficByDate;
import com.agencyamazon.domain.dto.SalesSummary;
import com.agencyamazon.repository.SalesAndTrafficByDateRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;


@Repository
@AllArgsConstructor
@Slf4j
public class SalesAndTrafficByDateRepositoryImpl implements SalesAndTrafficByDateRepository {
    private final MongoTemplate mongoTemplate;

    private static final String COLLECTION_NAME = "salesAndTrafficByDate";

    @Override
    public SalesAndTrafficByDate insertAll(SalesAndTrafficByDate entity) {
        return mongoTemplate.save(entity, COLLECTION_NAME);
    }

    @Override
    public Collection<SalesAndTrafficByDate> insertAll(List<SalesAndTrafficByDate> entities) {
        return mongoTemplate.insertAll(entities);
    }

    @Override
    public List<SalesAndTrafficByDate> getSalesAndTrafficByDateRange(Date startDate, Date endDate) {
        Query query = new Query(getCriteriaForDate(startDate, endDate));
        return mongoTemplate.find(query, SalesAndTrafficByDate.class);
    }

    @Override
    public SalesSummary getAggregatedStats() {
        AggregationResults<SalesSummary> results = mongoTemplate.aggregate(
                Aggregation.newAggregation(
                        SalesAndTrafficByDate.class,
                        Aggregation.group()
                                .sum("salesByDate.orderedProductSales.amount").as("orderedProductSales")
                                .sum("salesByDate.orderedProductSalesB2B.amount").as("orderedProductSalesB2B")
                                .sum("salesByDate.unitsOrdered").as("unitsOrdered")
                                .sum("salesByDate.unitsOrderedB2B").as("unitsOrderedB2B")
                                .sum("salesByDate.totalOrderItems").as("totalOrderItems")
                                .sum("salesByDate.totalOrderItemsB2B").as("totalOrderItemsB2B")
                                .avg("salesByDate.averageSalesPerOrderItem.amount").as("averageSalesPerOrderItem")
                                .avg("salesByDate.averageSalesPerOrderItemB2B.amount").as("averageSalesPerOrderItemB2B")
                                .avg("salesByDate.averageUnitsPerOrderItem").as("averageUnitsPerOrderItem")
                                .avg("salesByDate.averageUnitsPerOrderItemB2B").as("averageUnitsPerOrderItemB2B")
                                .avg("salesByDate.averageSellingPrice.amount").as("averageSellingPrice")
                                .avg("salesByDate.averageSellingPriceB2B.amount").as("averageSellingPriceB2B")
                        // here could be more fields, but that's enough for the task

                ), SalesSummary.class);
        return results.getUniqueMappedResult();

    }

    @Override
    public List<SalesAndTrafficByDate> getSalesAndTrafficByDates(List<Date> dates) {
        Query query = new Query(Criteria.where("date").in(dates));
        return mongoTemplate.find(query, SalesAndTrafficByDate.class);
    }

    private Criteria getCriteriaForDate(Date startDate, Date endDate) {
        return endDate == null
                ? Criteria.where("date").is(startDate)
                : Criteria.where("date").gte(startDate).lte(endDate);
    }

}
