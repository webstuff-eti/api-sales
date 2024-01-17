package com.tiagotibaes.controller.dto.resquest;


import com.tiagotibaes.domain.enumerated.TypeDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentRequestDTO {

    private String typeDocument;
    private String identificationNumber;
}
