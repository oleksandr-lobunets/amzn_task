package com.agencyamazon.service;

import com.agencyamazon.domain.model.report.sales.SalesAndTrafficByDate;
import com.agencyamazon.domain.dto.SalesSummary;

import java.util.Collection;
import java.util.Date;
import java.util.List;


public interface SalesAndTrafficByDateService {

    String saveEntity(SalesAndTrafficByDate entity);

    Collection<SalesAndTrafficByDate> save(List<SalesAndTrafficByDate> entities);

    List<SalesAndTrafficByDate> getSalesAndTrafficByDates(Date startDate, Date endDate);
    List<SalesAndTrafficByDate> getSalesAndTrafficByDates(List<Date> dates);

    SalesSummary getAggregatedStats();

}
