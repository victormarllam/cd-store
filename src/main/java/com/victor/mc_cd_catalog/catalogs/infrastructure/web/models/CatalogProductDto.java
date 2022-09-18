package com.victor.mc_cd_catalog.catalogs.infrastructure.web.models;

import com.victor.mc_cd_catalog.product.infrastructure.web.model.ProductDto;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CatalogProductDto {
    Integer id;
    ProductDto product;
    int money;
    int discount;
}
