package com.tiagotibaes.controller.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProductResponseDTO {

    private Integer id;
    private String description;
    private BigDecimal unitPrice;
}
