package com.agencyamazon.repository;

import com.agencyamazon.domain.model.report.sales.SalesAndTrafficByDate;
import com.agencyamazon.domain.dto.SalesSummary;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface SalesAndTrafficByDateRepository {
    SalesAndTrafficByDate insertAll(SalesAndTrafficByDate item);

    Collection<SalesAndTrafficByDate> insertAll(List<SalesAndTrafficByDate> items);

//    SalesAndTrafficByDateRepositoryImpl.SalesStats getStatsByDateRange(Date startDate, Date endDate);
    List<SalesAndTrafficByDate> getSalesAndTrafficByDateRange(Date startDate, Date endDate);

    SalesSummary getAggregatedStats();

    List<SalesAndTrafficByDate> getSalesAndTrafficByDates(List<Date> dates);
}
