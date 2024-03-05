package com.agencyamazon.entity.dao.report.sales;

import com.agencyamazon.entity.dao.report.sales.OrderedProductSales;
import com.agencyamazon.entity.dao.report.sales.OrderedProductSalesB2B;
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
