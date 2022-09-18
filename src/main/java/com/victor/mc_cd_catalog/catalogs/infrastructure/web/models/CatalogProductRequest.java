package com.victor.mc_cd_catalog.catalogs.infrastructure.web.models;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CatalogProductRequest {
    private int id;
    private int money;
    private Integer discount;
}
