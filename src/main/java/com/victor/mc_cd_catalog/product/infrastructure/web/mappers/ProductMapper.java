package com.victor.mc_cd_catalog.product.infrastructure.web.mappers;

import com.victor.mc_cd_catalog.product.domain.models.Product;
import com.victor.mc_cd_catalog.product.infrastructure.web.model.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ProductMapper {
    ProductDto toDto(Product product);
}
