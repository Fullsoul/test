package com.ssm.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
public class ProductDto {
    private Long productId;

    private String productName;

    private String barCode;

    private Long brandId;

    private Integer firstCategoryId;

    private Integer secondCategoryId;

    private BigDecimal price;

    private Integer publishStatus;

    private Integer auditStatus;

    private Float weight;

    private Float length;

    private Float height;

    private Float width;

    private String colorType;

    private Date productionDate;

    private String descript;

    private Date indate;

    private Date modifiedTime;
}
