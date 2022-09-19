package com.victor.mc_cd_catalog.infrastructure.model;

import com.victor.mc_cd_catalog.product.domain.use_cases.exceptions.ErrorType;
import lombok.Value;

@Value
public class RestError {
    ErrorType errorType;
    int errorCode;
    String message;
}
