package com.agencyamazon.service.impl;


import com.agencyamazon.domain.model.report.Report;
import com.agencyamazon.domain.model.report.sales.SalesAndTrafficByAsin;
import com.agencyamazon.domain.model.report.sales.SalesAndTrafficByDate;
import com.agencyamazon.service.SalesAndTrafficByAsinService;
import com.agencyamazon.service.SalesAndTrafficByDateService;
import com.agencyamazon.util.ReportUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@Slf4j
public class PopulateDbService implements CommandLineRunner {

    @Value("${application.file.report}")
    private String reportFile;

    private final SalesAndTrafficByAsinService salesAndTrafficByAsinService;
    private final SalesAndTrafficByDateService salesAndTrafficByDateService;

    public PopulateDbService(SalesAndTrafficByAsinService salesAndTrafficByAsinService, SalesAndTrafficByDateService salesAndTrafficByDateService) {
        this.salesAndTrafficByAsinService = salesAndTrafficByAsinService;
        this.salesAndTrafficByDateService = salesAndTrafficByDateService;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting DB population ...");
        Report report = ReportUtils.readReportFromFile(reportFile, Report.class);
        populateSalesAndTrafficByAsin(report.getSalesAndTrafficByAsin());
        populateSalesAndTrafficByDate(report.getSalesAndTrafficByDate());
        log.info("DB population has finished.");
    }

    private void populateSalesAndTrafficByAsin(List<SalesAndTrafficByAsin> items){
        salesAndTrafficByAsinService.saveItems(items);
    }

    private void populateSalesAndTrafficByDate(List<SalesAndTrafficByDate> items){
        salesAndTrafficByDateService.save(items);
    }


}
