package com.agencyamazon.entity.dao.report;

import com.agencyamazon.entity.dao.report.sales.SalesAndTrafficByAsin;
import com.agencyamazon.entity.dao.report.sales.SalesAndTrafficByDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Report {

    private ReportSpecification reportSpecification;
    private List<SalesAndTrafficByDate> salesAndTrafficByDate;
    private List<SalesAndTrafficByAsin> salesAndTrafficByAsin;

}
