package com.victor.mc_cd_catalog.catalogs.infrastructure.web;

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
public class CatalogProductController {
    private final FindCatalogProduct findCatalogProduct;
    private final CatalogProductMapper catalogProductMapper;

    @GetMapping("hello")
    private String hello() {
        return "hello world";
    }

    @GetMapping
    private CatalogResponse getCatalog(@RequestParam String title,
                                       @PageableDefault(value = 5, page = 0) Pageable pageable) {
        var catalogProducts = findCatalogProduct.findByTitleLike(title,pageable);
        return CatalogResponse.builder()
                .page(pageable.getPageNumber())
                .productCount(catalogProducts.size())
                .catalogProducts(catalogProductMapper.toDto(catalogProducts))
                .build();
    }
}
