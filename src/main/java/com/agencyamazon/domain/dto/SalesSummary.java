package com.agencyamazon.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
public class SalesSummary {
    private BigDecimal orderedProductSales;
    private BigDecimal orderedProductSalesB2B;
    private Integer unitsOrdered;
    private Integer unitsOrderedB2B;
    private Integer totalOrderItems;
    private Integer totalOrderItemsB2B;
    private BigDecimal averageSalesPerOrderItem;
    private BigDecimal averageSalesPerOrderItemB2B;
    private BigDecimal averageUnitsPerOrderItem;
    private BigDecimal averageUnitsPerOrderItemB2B;
    private BigDecimal averageSellingPrice;
    private BigDecimal averageSellingPriceB2B;
//    private Integer unitsRefunded;
//    private BigDecimal refundRate;
//    private Integer claimsGranted;
//    private BigDecimal claimsAmount;
//    private BigDecimal shippedProductSales;
//    private Integer unitsShipped;
//    private Integer ordersShipped;
//    private Integer browserPageViews;
//    private Integer browserPageViewsB2B;
//    private Integer mobileAppPageViews;
//    private Integer mobileAppPageViewsB2B;
//    private Integer pageViews;
//    private Integer pageViewsB2B;
//    private Integer browserSessions;
//    private Integer browserSessionsB2B;
//    private Integer mobileAppSessions;
//    private Integer mobileAppSessionsB2B;
//    private Integer sessions;
//    private Integer sessionsB2B;
//    private Double buyBoxPercentage;
//    private Double buyBoxPercentageB2B;
//    private Double orderItemSessionPercentage;
//    private Double orderItemSessionPercentageB2B;
//    private Double unitSessionPercentage;
//    private Double unitSessionPercentageB2B;
//    private Integer averageOfferCount;
//    private Integer averageParentItems;
//    private Integer feedbackReceived;
//    private Integer negativeFeedbackReceived;
//    private Double receivedNegativeFeedbackRate;

}
