package com.victor.mc_cd_catalog.catalogs.domain.ports.incoming;

import com.victor.mc_cd_catalog.catalogs.domain.models.CatalogProduct;
import com.victor.mc_cd_catalog.product.domain.models.Product;

public interface AddProductToCatalog {
    CatalogProduct addProductToCatalog(Product product);
}
