package com.victor.mc_cd_catalog.catalogs.infrastructure.web;

import com.victor.mc_cd_catalog.catalogs.domain.models.CatalogProductRequest;
import com.victor.mc_cd_catalog.catalogs.domain.ports.incoming.AddProductToCatalog;
import com.victor.mc_cd_catalog.catalogs.domain.ports.incoming.DeleteProductFromCatalog;
import com.victor.mc_cd_catalog.catalogs.infrastructure.web.mappers.CatalogProductMapper;
import com.victor.mc_cd_catalog.catalogs.infrastructure.web.models.CatalogProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalog-products")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CatalogProductController {
    private final AddProductToCatalog addProductToCatalog;
    private final DeleteProductFromCatalog deleteProductFromCatalog;
    private final CatalogProductMapper catalogProductMapper;

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    private CatalogProductDto addProductToCatalog(@RequestBody CatalogProductRequest catalogProductRequest) {
        return catalogProductMapper.toDto(
                addProductToCatalog.addProductToCatalog(catalogProductRequest));
    }

    @DeleteMapping("/{id}")
    private void deleteProductFromCatalog(@PathVariable int id) {
        deleteProductFromCatalog.deleteProductFromCatalog(id);
    }
}
