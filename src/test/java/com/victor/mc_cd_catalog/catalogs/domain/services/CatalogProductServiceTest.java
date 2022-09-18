package com.victor.mc_cd_catalog.catalogs.domain.services;

import com.victor.mc_cd_catalog.catalogs.domain.models.CatalogProduct;
import com.victor.mc_cd_catalog.catalogs.domain.models.CatalogProductRequest;
import com.victor.mc_cd_catalog.catalogs.infrastructure.repository.CatalogProductRepository;
import com.victor.mc_cd_catalog.product.domain.models.Product;
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
    CatalogProductRepository catalogProductRepository;
    @InjectMocks
    CatalogProductService tested;

    @Test
    void Given_NonExistingProduct_When_AddProductToCatalog_Then_ThrowIllegalArgumentException() {
        var anyCatalogProductRequest = createAnyCatalogProductRequest();
        when(productRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> tested.addProductToCatalog(anyCatalogProductRequest))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void Given_ExistingProduct_When_AddProductToCatalog_Then_CatalogProductIsCreated() {
        var catalogProductArgumentCaptor = ArgumentCaptor.forClass(CatalogProduct.class);
        var anyProduct = createAnyProduct();
        var anyCatalogProductRequest = createAnyCatalogProductRequest();
        when(productRepository.findById(any())).thenReturn(Optional.of(anyProduct));

        tested.addProductToCatalog(anyCatalogProductRequest);

        verify(catalogProductRepository, times(1)).save(catalogProductArgumentCaptor.capture());
        var result = catalogProductArgumentCaptor.getValue();

        assertThat(result.getId()).isEqualTo(anyProduct.getId());
        assertThat(result.getMoney()).isEqualTo(anyCatalogProductRequest.getMoney());
        assertThat(result.getDiscount()).isEqualTo(anyCatalogProductRequest.getDiscount());
    }

    private static CatalogProductRequest createAnyCatalogProductRequest() {
        return new CatalogProductRequest(ANY_ID_PRODUCT, ANY_MONEY, ANY_DISCOUNT);
    }

    private Product createAnyProduct() {
        return new Product(ANY_ID_PRODUCT);
    }
}