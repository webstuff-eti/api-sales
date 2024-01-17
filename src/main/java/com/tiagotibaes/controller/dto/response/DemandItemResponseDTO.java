package com.tiagotibaes.controller.dto.response;

import com.tiagotibaes.domain.entity.Demand;
import com.tiagotibaes.domain.entity.Product;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class DemandItemResponseDTO {

    private Integer id;
    private DemandItemResponseDTO demandItemResponseDTO;
    private ProductResponseDTO productResponseDTO;
    private Integer quantity;
}
