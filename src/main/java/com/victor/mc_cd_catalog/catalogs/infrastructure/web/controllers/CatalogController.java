package com.victor.mc_cd_catalog.catalogs.infrastructure.web.controllers;

import com.victor.mc_cd_catalog.catalogs.domain.ports.incoming.FindCatalogProduct;
import com.victor.mc_cd_catalog.catalogs.infrastructure.web.mappers.CatalogProductMapper;
import com.victor.mc_cd_catalog.catalogs.infrastructure.web.models.CatalogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CatalogController {
    private static final int PAGE_SIZE = 5;

    private final FindCatalogProduct findCatalogProduct;
    private final CatalogProductMapper catalogProductMapper;

    @GetMapping
    public CatalogResponse getCatalog(@RequestParam String title,
                                       @PageableDefault(value = PAGE_SIZE) Pageable pageable) {
        var catalogProducts = findCatalogProduct.findByTitleLike(title, pageable);
        return CatalogResponse.builder()
                .page(pageable.getPageNumber())
                .productCount(catalogProducts.getTotalElements())
                .catalogProducts(catalogProductMapper.toDto(catalogProducts.getContent()))
                .build();
    }
}
