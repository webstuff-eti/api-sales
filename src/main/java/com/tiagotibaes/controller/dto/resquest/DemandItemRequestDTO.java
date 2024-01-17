package com.tiagotibaes.controller.dto.resquest;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandItemRequestDTO {

    private Integer productId;
    private Integer quantity;
}
