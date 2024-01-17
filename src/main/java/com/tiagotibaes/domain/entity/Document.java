package com.tiagotibaes.domain.entity;


import com.tiagotibaes.domain.converter.TypeDocumentConverter;
import com.tiagotibaes.domain.enumerated.TypeDocument;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "document")
public class Document implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name ="type_document" ,length = 10, nullable = false)
    @Convert(converter = TypeDocumentConverter.class)
    private TypeDocument typeDocument;

    @NotNull
    @Column(name = "identification_number")
    private String identificationNumber;

    @Column(name = "included_date")
    private LocalDate includedDate;

    @Column(name = "update_Date")
    private LocalDate updateDate;

    //TODO: Inserir impl abaixo após migrar para MySQL
    /*
    Relacionamento: Muitos Documentos para um cliente
     */
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
