package com.tiagotibaes.controller.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tiagotibaes.domain.entity.Demand;
import com.tiagotibaes.domain.entity.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDTO {

    private String name;
    private LocalDate birthDate;
    private LocalDate includedDate;
    private LocalDate updateDate;
    private Set<DocumentResponseDTO> documents;
}
