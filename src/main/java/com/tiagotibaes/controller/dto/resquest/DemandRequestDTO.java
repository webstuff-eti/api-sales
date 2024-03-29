package com.tiagotibaes.controller.dto.resquest;

import com.tiagotibaes.domain.entity.Document;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandRequestDTO {
    private Integer clientId;
    private BigDecimal total;
    private List<DemandItemRequestDTO> items;
}

