package com.tiagotibaes.controller.dto.response;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemandResponseDTO {

    private Integer idDemand;
    List<DocumentResponseDTO> documents;
    private String nameClient;
    private BigDecimal total;
    private LocalDate dataDemand;
    private List<DemandItemResponseDTO> items;
}
