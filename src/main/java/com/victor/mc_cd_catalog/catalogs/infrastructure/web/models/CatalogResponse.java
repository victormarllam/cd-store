package com.victor.mc_cd_catalog.catalogs.infrastructure.web.models;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CatalogResponse {
    int page;
    long productCount;
    List<CatalogProductDto> catalogProducts;
}
