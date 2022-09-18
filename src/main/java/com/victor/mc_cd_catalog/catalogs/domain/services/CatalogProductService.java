package com.victor.mc_cd_catalog.catalogs.domain.services;

import com.victor.mc_cd_catalog.catalogs.domain.models.CatalogProduct;
import com.victor.mc_cd_catalog.catalogs.domain.models.CatalogProductRequest;
import com.victor.mc_cd_catalog.catalogs.domain.ports.incoming.AddProductToCatalog;
import com.victor.mc_cd_catalog.catalogs.domain.ports.incoming.DeleteProductFromCatalog;
import com.victor.mc_cd_catalog.catalogs.domain.ports.incoming.FindCatalogProduct;
import com.victor.mc_cd_catalog.catalogs.infrastructure.repository.CatalogProductRepository;
import com.victor.mc_cd_catalog.product.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CatalogProductService implements AddProductToCatalog, FindCatalogProduct, DeleteProductFromCatalog {
    private final ProductRepository productRepository;
    private final CatalogProductRepository catalogProductRepository;

    @Override
    public CatalogProduct addProductToCatalog(CatalogProductRequest catalogProductRequest) {
        var product = productRepository.findById(catalogProductRequest.getIdProduct())
                .orElseThrow(IllegalArgumentException::new);

        var catalogProduct = new CatalogProduct(product.getId(), product, catalogProductRequest.getMoney(),
                catalogProductRequest.getDiscount());

        return catalogProductRepository.save(catalogProduct);
    }

    @Override
    public List<CatalogProduct> findByTitleLike(String title, Pageable pageable) {
        return catalogProductRepository.findByTitleLike(title.toLowerCase(), pageable)
                .getContent();
    }

    @Override
    public void deleteProductFromCatalog(int id) {
        catalogProductRepository.deleteById(id);
    }
}
