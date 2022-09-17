package com.victor.mc_cd_catalog.catalogs.domain.services;

import com.victor.mc_cd_catalog.catalogs.domain.models.CatalogProduct;
import com.victor.mc_cd_catalog.catalogs.domain.ports.incoming.AddProductToCatalog;
import com.victor.mc_cd_catalog.catalogs.domain.ports.incoming.FindCatalogProduct;
import com.victor.mc_cd_catalog.catalogs.domain.ports.incoming.RemoveProductFromCatalog;
import com.victor.mc_cd_catalog.catalogs.infrastructure.repository.CatalogProductRepository;
import com.victor.mc_cd_catalog.product.domain.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CatalogProductService implements AddProductToCatalog, FindCatalogProduct, RemoveProductFromCatalog {
    private final CatalogProductRepository catalogProductRepository;

    @Override
    public CatalogProduct addProductToCatalog(Product product) {
        return null;
    }

    @Override
    @Transactional
    public List<CatalogProduct> findByTitleLike(String title, Pageable pageable) {
        return catalogProductRepository.findLikeTitle(title.toLowerCase(), pageable)
                .getContent();
    }

    @Override
    public void removeProductFromCatalog(CatalogProduct catalogProduct) {

    }
}
