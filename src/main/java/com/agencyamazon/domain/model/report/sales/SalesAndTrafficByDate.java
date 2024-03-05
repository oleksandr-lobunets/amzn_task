package com.agencyamazon.entity.dao.report.sales;

import com.agencyamazon.entity.dao.report.traffic.TrafficByDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
@Getter
@Setter
@Document(collection = "salesAndTrafficByDate")
public class SalesAndTrafficByDate {

    @Id
    private String id;
    private Date date;
    private SalesByDate salesByDate;
    private TrafficByDate trafficByDate;


}

