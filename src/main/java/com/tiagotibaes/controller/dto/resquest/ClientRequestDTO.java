package com.tiagotibaes.controller.dto.resquest;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDTO {
    private String name;

    //@JsonDeserialize(using = LocalDateDeserializer.class)
    //@JsonFormat(pattern = "dd/MM/yyyy")
    private String birthDate;

    private List<DocumentRequestDTO> documents;
}
