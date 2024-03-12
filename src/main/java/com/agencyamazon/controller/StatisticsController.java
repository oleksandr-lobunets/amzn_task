package com.agencyamazon.controller;


import com.agencyamazon.domain.model.report.sales.SalesAndTrafficByAsin;
import com.agencyamazon.domain.model.report.sales.SalesAndTrafficByDate;
import com.agencyamazon.domain.dto.AsinsSummary;
import com.agencyamazon.domain.dto.SalesSummary;
import com.agencyamazon.service.SalesAndTrafficByAsinService;
import com.agencyamazon.service.SalesAndTrafficByDateService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/statistics")
@AllArgsConstructor
public class StatisticsController {

    private final SalesAndTrafficByAsinService salesAndTrafficByAsinService;
    private final SalesAndTrafficByDateService salesAndTrafficByDateService;


    //    3) Вивід статистики по вказаній даті (або проміжку дат)
    @GetMapping("/days")
    public ResponseEntity<List<SalesAndTrafficByDate>> getSales(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        List<SalesAndTrafficByDate> result = salesAndTrafficByDateService.getSalesAndTrafficByDates(startDate, endDate);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    //4) Вивід статистики по вказаному ASIN (або списку ASINs)
    @GetMapping("/asins")
    public ResponseEntity<List<SalesAndTrafficByAsin>> getAsins(@RequestParam List<String> asins) {
        List<SalesAndTrafficByAsin> result = salesAndTrafficByAsinService.findAllByParentAsinIn(asins);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 5) Вивід сумарної статистики по всім датам
    @GetMapping("/days/sum")
    public SalesSummary getSalesBy() {
        return salesAndTrafficByDateService.getAggregatedStats();
    }

    //6) Вивід сумарної статистики по всім ASIN
    @GetMapping("/asins/sum")
    public AsinsSummary getSales() {
        return salesAndTrafficByAsinService.getAggregatedStats();
    }

}
