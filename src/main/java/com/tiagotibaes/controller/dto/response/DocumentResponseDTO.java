package com.tiagotibaes.controller.dto.response;


import com.tiagotibaes.domain.enumerated.TypeDocument;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentResponseDTO {

    private TypeDocument typeDocument;
    private String identificationNumber;
}
