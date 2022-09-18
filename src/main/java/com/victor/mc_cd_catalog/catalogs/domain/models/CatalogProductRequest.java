package com.victor.mc_cd_catalog.catalogs.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CatalogProductRequest {
    private int idProduct;
    private int money;
    private Integer discount;
}
