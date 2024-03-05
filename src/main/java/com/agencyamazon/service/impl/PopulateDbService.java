package com.agencyamazon.service;


import com.agencyamazon.domain.model.report.Report;
import com.agencyamazon.domain.model.report.sales.SalesAndTrafficByAsin;
import com.agencyamazon.domain.model.report.sales.SalesAndTrafficByDate;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
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
        Report report = readReportFromFile();
        populateSalesAndTrafficByAsin(report.getSalesAndTrafficByAsin());
        populateSalesAndTrafficByDate(report.getSalesAndTrafficByDate());
        log.info("DB population has finished.");
    }

    private void populateSalesAndTrafficByAsin(List<SalesAndTrafficByAsin> items){
        salesAndTrafficByAsinService.saveItems(items);
    }

    private void populateSalesAndTrafficByDate(List<SalesAndTrafficByDate> items){
        salesAndTrafficByDateService.insertEntities(items);
    }


    private Report readReportFromFile() throws IOException {
        return new ObjectMapper().readValue(Paths.get(reportFile).toFile(), Report.class);
    }
}
