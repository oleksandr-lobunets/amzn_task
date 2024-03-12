package com.agencyamazon.domain.model.report.sales;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractItem {
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal amount;
    private String currencyCode;
}
