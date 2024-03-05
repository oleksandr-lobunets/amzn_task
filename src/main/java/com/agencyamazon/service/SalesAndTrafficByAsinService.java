package com.agencyamazon.entity.service;

import com.agencyamazon.entity.traffic.SalesByAsin;
import com.agencyamazon.report.traffic.SalesAndTrafficByAsin;
import com.agencyamazon.report.traffic.SalesAndTrafficByDate;
import com.agencyamazon.repository.SalesAndTrafficByAsinRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class SalesAndTrafficByAsinService {
    private final SalesAndTrafficByAsinRepository salesAndTrafficByAsinRepository;


    public String saveItem(SalesAndTrafficByAsin item) {
        SalesAndTrafficByAsin saved = salesAndTrafficByAsinRepository.save(item);
        log.info("Created SalesAndTrafficByAsin: {}", saved );
        return saved.getId();
    }

    @Transactional
    public List<SalesAndTrafficByAsin> saveItems(List<SalesAndTrafficByAsin> items) {
        return salesAndTrafficByAsinRepository.saveAll(items);
    }

}
