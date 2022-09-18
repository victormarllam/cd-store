package com.victor.mc_cd_catalog.catalogs.infrastructure.repository;

import com.victor.mc_cd_catalog.catalogs.domain.models.CatalogProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;

@Repository
public interface CatalogProductRepository extends PagingAndSortingRepository<CatalogProduct, Integer> {
    @Query(name = "CatalogProduct.findLikeTitle")
    @QueryHints(
            @QueryHint(name = "org.hibernate.cacheable", value = "true")
    )
    Page<CatalogProduct> findLikeTitle(String title, Pageable pageable);
}
