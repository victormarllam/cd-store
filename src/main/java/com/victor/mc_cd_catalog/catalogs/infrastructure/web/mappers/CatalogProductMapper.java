package com.victor.mc_cd_catalog.catalogs.infrastructure.web.mappers;

import com.victor.mc_cd_catalog.catalogs.domain.models.CatalogProduct;
import com.victor.mc_cd_catalog.catalogs.infrastructure.web.models.CatalogProductDto;
import com.victor.mc_cd_catalog.product.infrastructure.web.mappers.ProductMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = ProductMapper.class
)
public interface CatalogProductMapper {
    CatalogProductDto toDto(CatalogProduct catalogProduct);

    List<CatalogProductDto> toDto(List<CatalogProduct> catalogProducts);
}
