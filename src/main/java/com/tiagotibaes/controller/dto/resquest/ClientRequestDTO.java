package com.tiagotibaes.controller.dto.resquest;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDTO {
    private String name;

    //@JsonDeserialize(using = LocalDateDeserializer.class)
    //@JsonFormat(pattern = "dd/MM/yyyy")
    private String birthDate;

    private Set<DocumentRequestDTO> documents;
}
