package com.agencyamazon.service.impl;

import com.agencyamazon.domain.dto.SalesSummary;
import com.agencyamazon.domain.model.report.sales.SalesAndTrafficByDate;
import com.agencyamazon.repository.SalesAndTrafficByDateRepository;
import com.agencyamazon.service.SalesAndTrafficByDateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SalesAndTrafficByDateServiceImpl implements SalesAndTrafficByDateService {
    private final SalesAndTrafficByDateRepository salesAndTrafficByDateRepository;


    @CachePut("Dates")
    public String saveEntity(SalesAndTrafficByDate entity) {
        SalesAndTrafficByDate saved = salesAndTrafficByDateRepository.insertAll(entity);
        log.info("Created SalesAndTrafficByAsin: {}", saved );
        return saved.getId();
    }

    @CachePut("Dates")
    public Collection<SalesAndTrafficByDate> save(List<SalesAndTrafficByDate> entities) {
        return salesAndTrafficByDateRepository.insertAll(entities);
    }


    @Cacheable("Dates")
    public List<SalesAndTrafficByDate> getSalesAndTrafficByDates(Date startDate, Date endDate) {
        return salesAndTrafficByDateRepository.getSalesAndTrafficByDateRange(startDate, endDate);
    }

    @Cacheable("Dates")
    public List<SalesAndTrafficByDate> getSalesAndTrafficByDates(List<Date> dates) {
        return salesAndTrafficByDateRepository.getSalesAndTrafficByDates(dates);
    }

    @Cacheable("DateStats")
    public SalesSummary getAggregatedStats() {
        return salesAndTrafficByDateRepository.getAggregatedStats();
    }
}
