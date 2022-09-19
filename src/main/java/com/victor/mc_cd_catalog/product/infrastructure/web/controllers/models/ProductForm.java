package com.victor.mc_cd_catalog.product.infrastructure.web.controllers.models;

import com.victor.mc_cd_catalog.product.domain.models.ProductType;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@Builder
public class ProductForm {
    @NotBlank
    String uid;
    @NotBlank
    String title;
    @NotNull
    ProductType productType;
}
