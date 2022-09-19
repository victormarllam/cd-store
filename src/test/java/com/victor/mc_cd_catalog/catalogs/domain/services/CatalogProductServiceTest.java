package com.victor.mc_cd_catalog.catalogs.domain.services;

import com.victor.mc_cd_catalog.catalogs.domain.models.CatalogProduct;
import com.victor.mc_cd_catalog.catalogs.domain.ports.outcoming.SaveCatalogProduct;
import com.victor.mc_cd_catalog.product.domain.models.Product;
import com.victor.mc_cd_catalog.product.domain.use_cases.exceptions.NonExistingProductException;
import com.victor.mc_cd_catalog.product.infrastructure.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatalogProductServiceTest {
    private static final int ANY_ID_PRODUCT = 1;
    private static final int ANY_MONEY = 1;
    private static final int ANY_DISCOUNT = 1;

    @Mock
    ProductRepository productRepository;
    @Mock
    SaveCatalogProduct saveCatalogProduct;
    @InjectMocks
    CatalogProductService tested;

    @Test
    void Given_NonExistingProduct_When_CreateOrUpdateProductToCatalog_Then_ThrowIllegalArgumentException() {
        var anyCatalogProduct = createAnyCatalogProduct();
        when(productRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> tested.createOrUpdateProductToCatalog(anyCatalogProduct))
                .isInstanceOf(NonExistingProductException.class);
    }

    @Test
    void Given_ExistingProduct_When_CreateOrUpdateProductToCatalog_Then_CatalogProductIsCreated() {
        var catalogProductArgumentCaptor = ArgumentCaptor.forClass(CatalogProduct.class);
        var anyProduct = createAnyProduct();
        var anyCatalogProduct = createAnyCatalogProduct();
        when(productRepository.findById(any())).thenReturn(Optional.of(anyProduct));

        tested.createOrUpdateProductToCatalog(anyCatalogProduct);

        verify(saveCatalogProduct, times(1)).save(catalogProductArgumentCaptor.capture());
        var result = catalogProductArgumentCaptor.getValue();

        assertThat(result.getId()).isEqualTo(anyProduct.getId());
        assertThat(result.getMoney()).isEqualTo(anyCatalogProduct.getMoney());
        assertThat(result.getDiscount()).isEqualTo(anyCatalogProduct.getDiscount());
    }

    private static CatalogProduct createAnyCatalogProduct() {
        return new CatalogProduct(ANY_ID_PRODUCT, ANY_MONEY,ANY_DISCOUNT);
    }

    private Product createAnyProduct() {
        return new Product(ANY_ID_PRODUCT);
    }
}