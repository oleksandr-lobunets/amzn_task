package com.agencyamazon.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
public class AsinsSummary {
    private Integer unitsOrdered;
    private Integer unitsOrderedB2B;
    private BigDecimal orderedProductSales;
    private BigDecimal orderedProductSalesB2B;
    private Integer totalOrderItems;
    private Integer totalOrderItemsB2B;

}
