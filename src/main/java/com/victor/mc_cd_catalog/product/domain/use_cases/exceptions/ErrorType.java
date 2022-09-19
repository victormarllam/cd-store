package com.victor.mc_cd_catalog.product.domain.use_cases.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorType {
    NON_EXISTING_PRODUCT("The indicated product doesn't exist");
    private final String defaultMessage;
}
