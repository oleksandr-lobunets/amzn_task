package com.agencyamazon.entity.dao.report.sales;

import com.agencyamazon.entity.dao.report.sales.AverageSalesPerOrderItem;
import com.agencyamazon.entity.dao.report.sales.AverageSalesPerOrderItemB2B;
import com.agencyamazon.entity.dao.report.sales.AverageSellingPrice;
import com.agencyamazon.entity.dao.report.sales.AverageSellingPriceB2B;
import com.agencyamazon.entity.dao.report.sales.ClaimsAmount;
import com.agencyamazon.entity.dao.report.sales.OrderedProductSales;
import com.agencyamazon.entity.dao.report.sales.OrderedProductSalesB2B;
import com.agencyamazon.entity.dao.report.sales.ShippedProductSales;
import lombok.Data;

@Data
public class SalesByDate {

    private OrderedProductSales orderedProductSales;
    private OrderedProductSalesB2B orderedProductSalesB2B;
    private int unitsOrdered;
    private int unitsOrderedB2B;
    private int totalOrderItems;
    private int totalOrderItemsB2B;
    private AverageSalesPerOrderItem averageSalesPerOrderItem;
    private AverageSalesPerOrderItemB2B averageSalesPerOrderItemB2B;
    private double averageUnitsPerOrderItem;
    private double averageUnitsPerOrderItemB2B;
    private AverageSellingPrice averageSellingPrice;
    private AverageSellingPriceB2B averageSellingPriceB2B;
    private int unitsRefunded;
    private double refundRate;
    private int claimsGranted;
    private ClaimsAmount claimsAmount;
    private ShippedProductSales shippedProductSales;
    private int unitsShipped;
    private int ordersShipped;

}
