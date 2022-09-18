package com.victor.mc_cd_catalog.catalogs.domain.ports.outcoming;

import com.victor.mc_cd_catalog.catalogs.domain.models.CatalogProduct;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FindCatalogProductsByTitleLike {
    List<CatalogProduct> findByTitleLike(String title, Pageable pageable);
}
