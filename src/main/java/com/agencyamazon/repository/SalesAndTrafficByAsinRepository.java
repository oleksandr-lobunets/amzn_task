package com.agencyamazon.repository;

import com.agencyamazon.domain.model.report.sales.SalesAndTrafficByAsin;
import com.agencyamazon.domain.dto.AsinsSummary;

import java.util.Collection;
import java.util.List;

public interface SalesAndTrafficByAsinRepository {
    List<SalesAndTrafficByAsin> findAllByParentAsinIn(List<String> asins);

    SalesAndTrafficByAsin save(SalesAndTrafficByAsin item);

    Collection<SalesAndTrafficByAsin> saveAll(List<SalesAndTrafficByAsin> items);

    AsinsSummary getAggregatedStats();

    void deleteAll(List<String> ids);
}
