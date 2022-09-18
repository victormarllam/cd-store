package com.victor.mc_cd_catalog.catalogs.domain.ports.outcoming;

import com.victor.mc_cd_catalog.catalogs.domain.models.CatalogProduct;

public interface SaveCatalogProduct {
    CatalogProduct save(CatalogProduct catalogProduct);
}
