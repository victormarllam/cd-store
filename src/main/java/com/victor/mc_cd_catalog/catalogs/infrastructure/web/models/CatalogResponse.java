package com.victor.mc_cd_catalog.catalogs.infrastructure.web.models;

import com.victor.mc_cd_catalog.catalogs.domain.models.CatalogProduct;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CatalogResponse {
    int page;
    int productCount;
    List<CatalogProductDto> catalogProducts;
}
