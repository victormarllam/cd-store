package com.victor.mc_cd_catalog.catalogs.domain.ports.outcoming;

import com.victor.mc_cd_catalog.catalogs.domain.models.CatalogProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindCatalogProductsByTitleLike {
    Page<CatalogProduct> findByTitleLike(String title, Pageable pageable);
}
