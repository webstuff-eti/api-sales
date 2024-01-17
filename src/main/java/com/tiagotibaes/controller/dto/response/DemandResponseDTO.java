package com.tiagotibaes.controller.dto.response;

import com.tiagotibaes.domain.entity.Client;
import com.tiagotibaes.domain.entity.DemandItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@Setter
@Getter
public class DemandResponseDTO {

    private Integer id;
    private String description;
    private LocalDate dataDemand;
    private BigDecimal total;
    private Client client;
    private List<DemandItemResponseDTO> demandItemResponseDTOS;
}
