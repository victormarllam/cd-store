package com.victor.mc_cd_catalog.product.infrastructure.web.model;

import com.victor.mc_cd_catalog.product.domain.models.ProductType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductDto {
    Integer id;
    String uid;
    String title;
    ProductType productType;
}
