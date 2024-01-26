package com.tiagotibaes.controller.dto.response;


import lombok.*;

import java.math.BigDecimal;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemandItemResponseDTO {

    private String productDescription;
    private BigDecimal productUnitPrice;
    private Integer quantityOfProducts;
}
