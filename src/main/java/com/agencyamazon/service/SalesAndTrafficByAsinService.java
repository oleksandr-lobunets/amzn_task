package com.agencyamazon.service;

import com.agencyamazon.domain.model.report.sales.SalesAndTrafficByAsin;
import com.agencyamazon.domain.dto.AsinsSummary;

import java.util.Collection;
import java.util.List;


public interface SalesAndTrafficByAsinService {

    Collection<SalesAndTrafficByAsin> saveItems(List<SalesAndTrafficByAsin> items);

    List<SalesAndTrafficByAsin> findAllByParentAsinIn(List<String> asins);

    AsinsSummary getAggregatedStats();

}
