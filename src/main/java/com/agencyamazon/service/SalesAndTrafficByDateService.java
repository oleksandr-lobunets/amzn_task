package com.agencyamazon.entity.service;

import com.agencyamazon.report.traffic.SalesAndTrafficByAsin;
import com.agencyamazon.report.traffic.SalesAndTrafficByDate;
import com.agencyamazon.repository.SalesAndTrafficByDateRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class SalesAndTrafficByDateService {
    private final SalesAndTrafficByDateRepository salesAndTrafficByDateRepository;


    public String saveItem(SalesAndTrafficByDate item) {
        SalesAndTrafficByDate saved = salesAndTrafficByDateRepository.save(item);
        log.info("Created SalesAndTrafficByAsin: {}", saved );
        return saved.getId();
    }

    @Transactional
    public List<SalesAndTrafficByDate> saveItems(List<SalesAndTrafficByDate> items) {
        return salesAndTrafficByDateRepository.saveAll(items);
    }

}
