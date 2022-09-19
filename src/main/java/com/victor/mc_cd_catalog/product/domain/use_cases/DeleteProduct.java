package com.victor.mc_cd_catalog.product.domain.use_cases;

import com.victor.mc_cd_catalog.product.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteProduct {
    private final ProductRepository productRepository;

    public void deleteProductById(Integer idProduct) {
        productRepository.deleteById(idProduct);
    }
}
