package com.victor.mc_cd_catalog.catalogs.domain.ports.incoming;

import com.victor.mc_cd_catalog.catalogs.domain.models.CatalogProduct;

public interface RemoveProductFromCatalog {
    void removeProductFromCatalog(CatalogProduct catalogProduct);
}
