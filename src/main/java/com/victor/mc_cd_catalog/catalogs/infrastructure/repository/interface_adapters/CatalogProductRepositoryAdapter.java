package com.victor.mc_cd_catalog.catalogs.infrastructure.repository.interface_adapters;

import com.victor.mc_cd_catalog.catalogs.domain.models.CatalogProduct;
import com.victor.mc_cd_catalog.catalogs.domain.ports.outcoming.DeleteCatalogProduct;
import com.victor.mc_cd_catalog.catalogs.domain.ports.outcoming.FindCatalogProductsByTitleLike;
import com.victor.mc_cd_catalog.catalogs.domain.ports.outcoming.SaveCatalogProduct;
import com.victor.mc_cd_catalog.catalogs.infrastructure.repository.CatalogProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CatalogProductRepositoryAdapter implements DeleteCatalogProduct, FindCatalogProductsByTitleLike,
        SaveCatalogProduct {

    private final CatalogProductRepository catalogProductRepository;

    @Override
    public void deleteById(int id) {
        catalogProductRepository.deleteById(id);
    }

    @Override
    public List<CatalogProduct> findByTitleLike(String title, Pageable pageable) {
        return catalogProductRepository.findByTitleLike(title, pageable)
                .getContent();
    }

    @Override
    public CatalogProduct save(CatalogProduct catalogProduct) {
        return catalogProductRepository.save(catalogProduct);
    }
}
