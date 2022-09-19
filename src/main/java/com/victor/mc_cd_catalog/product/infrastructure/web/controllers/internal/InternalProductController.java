package com.victor.mc_cd_catalog.product.infrastructure.web.controllers.internal;

import com.victor.mc_cd_catalog.product.domain.use_cases.AddProduct;
import com.victor.mc_cd_catalog.product.domain.use_cases.DeleteProduct;
import com.victor.mc_cd_catalog.product.domain.use_cases.exceptions.NonExistingProductException;
import com.victor.mc_cd_catalog.product.infrastructure.repository.ProductRepository;
import com.victor.mc_cd_catalog.product.infrastructure.web.controllers.models.ProductForm;
import com.victor.mc_cd_catalog.product.infrastructure.web.mappers.ProductMapper;
import com.victor.mc_cd_catalog.product.infrastructure.web.model.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/internal/products")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InternalProductController {
    private final ProductMapper productMapper;
    private final AddProduct addProduct;
    private final DeleteProduct deleteProduct;
    private final ProductRepository productRepository;

    @GetMapping
    public List<ProductDto> findAll() {
        return productMapper.toDto(productRepository.findAll());
    }

    @PostMapping
    public ProductDto save(@RequestBody @Valid ProductForm productForm) {
        return productMapper.toDto(addProduct.addProduct(
                productMapper.toEntity(productForm)
        ));
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Integer id) {
        return productMapper.toDto(productRepository.findById(id)
                .orElseThrow(NonExistingProductException::new));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        deleteProduct.deleteProductById(id);
    }
}
