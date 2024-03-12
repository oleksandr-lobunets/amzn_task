package com.agencyamazon.service.impl;


import com.agencyamazon.domain.dto.AsinsSummary;
import com.agencyamazon.domain.model.report.sales.SalesAndTrafficByAsin;
import com.agencyamazon.repository.SalesAndTrafficByAsinRepository;
import com.agencyamazon.service.SalesAndTrafficByAsinService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SalesAndTrafficByAsinServiceImpl implements SalesAndTrafficByAsinService {
    private final SalesAndTrafficByAsinRepository salesAndTrafficByAsinRepository;

    @CachePut("Asins")
    @Override
    public Collection<SalesAndTrafficByAsin> saveItems(List<SalesAndTrafficByAsin> items) {
        return salesAndTrafficByAsinRepository.saveAll(items);
    }

    @Cacheable("Asins")
    public List<SalesAndTrafficByAsin> findAllByParentAsinIn(List<String> asins) {
        return salesAndTrafficByAsinRepository.findAllByParentAsinIn(asins);
    }

    @Cacheable("AsinStats")
    public AsinsSummary getAggregatedStats() {
        return salesAndTrafficByAsinRepository.getAggregatedStats();
    }

}
