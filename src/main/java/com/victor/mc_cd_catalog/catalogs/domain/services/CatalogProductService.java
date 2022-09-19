package com.victor.mc_cd_catalog.catalogs.domain.services;

import com.victor.mc_cd_catalog.catalogs.domain.models.CatalogProduct;
import com.victor.mc_cd_catalog.catalogs.domain.ports.incoming.CreateOrUpdateProductToCatalog;
import com.victor.mc_cd_catalog.catalogs.domain.ports.incoming.DeleteProductFromCatalog;
import com.victor.mc_cd_catalog.catalogs.domain.ports.incoming.FindCatalogProduct;
import com.victor.mc_cd_catalog.catalogs.domain.ports.outcoming.DeleteCatalogProduct;
import com.victor.mc_cd_catalog.catalogs.domain.ports.outcoming.FindCatalogProductsByTitleLike;
import com.victor.mc_cd_catalog.catalogs.domain.ports.outcoming.SaveCatalogProduct;
import com.victor.mc_cd_catalog.product.domain.use_cases.exceptions.NonExistingProductException;
import com.victor.mc_cd_catalog.product.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CatalogProductService implements CreateOrUpdateProductToCatalog, FindCatalogProduct, DeleteProductFromCatalog {
    private final ProductRepository productRepository;
    private final DeleteCatalogProduct deleteCatalogProduct;
    private final FindCatalogProductsByTitleLike findCatalogProductsByTitleLike;
    private final SaveCatalogProduct saveCatalogProduct;

    @Override
    public CatalogProduct createOrUpdateProductToCatalog(CatalogProduct catalogProduct) {
        var product = productRepository.findById(catalogProduct.getId())
                .orElseThrow(NonExistingProductException::new);

        catalogProduct.setProduct(product);

        return saveCatalogProduct.save(catalogProduct);
    }

    @Override
    public Page<CatalogProduct> findByTitleLike(String title, Pageable pageable) {
        return findCatalogProductsByTitleLike.findByTitleLike(title.toLowerCase(), pageable);
    }

    @Override
    public void deleteProductFromCatalog(int id) {
        deleteCatalogProduct.deleteById(id);
    }
}
