package com.victor.mc_cd_catalog.product.infrastructure.web.controllers.internal;

import com.victor.mc_cd_catalog.product.domain.models.Product;
import com.victor.mc_cd_catalog.product.domain.use_cases.AddProduct;
import com.victor.mc_cd_catalog.product.domain.use_cases.DeleteProduct;
import com.victor.mc_cd_catalog.product.infrastructure.repository.ProductRepository;
import com.victor.mc_cd_catalog.product.infrastructure.web.mappers.ProductMapper;
import com.victor.mc_cd_catalog.product.infrastructure.web.model.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/internal/products")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InternalProductController {
    private final ProductMapper productMapper;
    private final AddProduct addProduct;
    private final DeleteProduct deleteProduct;
    private final ProductRepository productRepository;

    @GetMapping
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @PostMapping
    public ProductDto save(@RequestBody @Valid Product product) {
        return productMapper.toDto(addProduct.addProduct(product));
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Integer id) {
        return productRepository.findById(id)
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        deleteProduct.deleteProductById(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
