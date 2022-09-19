package com.victor.mc_cd_catalog.product.infrastructure.web.mappers;

import com.victor.mc_cd_catalog.product.domain.models.Product;
import com.victor.mc_cd_catalog.product.infrastructure.web.controllers.models.ProductForm;
import com.victor.mc_cd_catalog.product.infrastructure.web.model.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductMapper {
    ProductDto toDto(Product product);

    Product toEntity(ProductForm productForm);

    List<ProductDto> toDto(List<Product> product);
}
