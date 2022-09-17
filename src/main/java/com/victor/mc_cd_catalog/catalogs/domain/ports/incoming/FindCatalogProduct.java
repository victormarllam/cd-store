package com.victor.mc_cd_catalog.catalogs.domain.ports.incoming;

import com.victor.mc_cd_catalog.catalogs.domain.models.CatalogProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FindCatalogProduct {
    List<CatalogProduct> findByTitleLike(String title, Pageable pageable);
}
