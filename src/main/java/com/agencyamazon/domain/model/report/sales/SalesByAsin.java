package com.agencyamazon.domain.model.report.sales;

import lombok.Data;

@Data
public class SalesByAsin {
    private int unitsOrdered;
    private int unitsOrderedB2B;
    private OrderedProductSales orderedProductSales;
    private OrderedProductSalesB2B orderedProductSalesB2B;
    private int totalOrderItems;
    private int totalOrderItemsB2B;
}
