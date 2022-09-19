package com.victor.mc_cd_catalog.catalogs.infrastructure.web.models;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.PositiveOrZero;

@Value
@Builder
public class CatalogProductForm {
    int id;
    @PositiveOrZero
    int money;
    Integer discount;
}
