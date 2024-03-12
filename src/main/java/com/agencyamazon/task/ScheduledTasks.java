package com.agencyamazon.task;


import com.agencyamazon.domain.model.report.Report;
import com.agencyamazon.domain.model.report.sales.SalesAndTrafficByAsin;
import com.agencyamazon.domain.model.report.sales.SalesAndTrafficByDate;
import com.agencyamazon.service.SalesAndTrafficByAsinService;
import com.agencyamazon.service.SalesAndTrafficByDateService;
import com.agencyamazon.util.ReportUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ScheduledTasks {

    private final SalesAndTrafficByAsinService salesAndTrafficByAsinService;
    private final SalesAndTrafficByDateService salesAndTrafficByDateService;

    @Value("${application.file.report}")
    private String reportFile;

    public ScheduledTasks(SalesAndTrafficByAsinService salesAndTrafficByAsinService, SalesAndTrafficByDateService salesAndTrafficByDateService) {
        this.salesAndTrafficByAsinService = salesAndTrafficByAsinService;
        this.salesAndTrafficByDateService = salesAndTrafficByDateService;
    }

    @Scheduled(initialDelayString = "${application.initialDelay.in.milliseconds}", fixedDelayString = "${application.fixedRate.in.milliseconds}")
    public void statisticUpdate() throws IOException {
        log.info("statisticUpdate starting...");
        Report report = ReportUtils.readReportFromFile(reportFile, Report.class);
        processSalesAndTrafficByDate(report);
        processSalesAndTrafficByAsin(report);

        log.info("statisticUpdate finished.");
    }

    private void processSalesAndTrafficByDate(Report report) {
        List<Date> reportDates = report.getSalesAndTrafficByDate().stream().map(SalesAndTrafficByDate::getDate).toList();

        //todo get from the first record in json
        List<SalesAndTrafficByDate> existing = salesAndTrafficByDateService.getSalesAndTrafficByDates(reportDates);
        Set<Date> existingDates = existing.stream()
                .map(SalesAndTrafficByDate::getDate)
                .collect(Collectors.toSet());

        List<SalesAndTrafficByDate> newDates = report.getSalesAndTrafficByDate()
                .stream().filter(a -> !existingDates.contains(a.getDate())).toList();


        salesAndTrafficByDateService.save(newDates);
    }


    private void processSalesAndTrafficByAsin(Report report) {

        List<String> reportAsins = report.getSalesAndTrafficByAsin().stream().map(SalesAndTrafficByAsin::getParentAsin).toList();

        List<SalesAndTrafficByAsin> existing = salesAndTrafficByAsinService.findAllByParentAsinIn(reportAsins);
        Set<String> existingAsins = existing.stream()
                .map(SalesAndTrafficByAsin::getParentAsin)
                .collect(Collectors.toSet());

        List<SalesAndTrafficByAsin> newAsins = report.getSalesAndTrafficByAsin()
                .stream().filter(a -> !existingAsins.contains(a.getParentAsin())).toList();

        salesAndTrafficByAsinService.saveItems(newAsins);
    }
}
