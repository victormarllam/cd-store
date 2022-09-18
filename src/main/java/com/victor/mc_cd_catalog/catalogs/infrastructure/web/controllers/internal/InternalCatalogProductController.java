package com.victor.mc_cd_catalog.catalogs.infrastructure.web.controllers.internal;

import com.victor.mc_cd_catalog.catalogs.domain.ports.incoming.CreateOrUpdateProductToCatalog;
import com.victor.mc_cd_catalog.catalogs.domain.ports.incoming.DeleteProductFromCatalog;
import com.victor.mc_cd_catalog.catalogs.infrastructure.web.mappers.CatalogProductMapper;
import com.victor.mc_cd_catalog.catalogs.infrastructure.web.models.CatalogProductDto;
import com.victor.mc_cd_catalog.catalogs.infrastructure.web.models.CatalogProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal/catalog-products")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InternalCatalogProductController {
    private final CatalogProductMapper catalogProductMapper;

    private final DeleteProductFromCatalog deleteProductFromCatalog;
    private final CreateOrUpdateProductToCatalog createOrUpdateProductToCatalog;

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    public CatalogProductDto addProductToCatalog(@RequestBody CatalogProductRequest catalogProductRequest) {
        var result = createOrUpdateProductToCatalog.createOrUpdateProductToCatalog(
                catalogProductMapper.toEntity(catalogProductRequest));

        return catalogProductMapper.toDto(result);
    }

    @DeleteMapping("/{id}")
    public void deleteProductFromCatalog(@PathVariable int id) {
        deleteProductFromCatalog.deleteProductFromCatalog(id);
    }
}
