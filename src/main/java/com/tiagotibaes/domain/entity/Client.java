package com.tiagotibaes.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<Demand> demands;

    /* Incluir campos na migração do H2 para MySQL
    @Column(name = "birth_data")
    private LocalDate birthDate;

    @Column(name = "included_date")
    private LocalDate includedDate;

    @Column(name = "updateDate")
    private LocalDate update_Date;

    //TODO: Quando migrar para MySQL, verificar se há esta implementação
    //Listar os documentos do cliente
    @OneToMany(mappedBy= "document", fetch = FetchType.LAZY)
    private List<Document> documents;
    */

    public Client(Integer id, String name, String cpf){
        this.id = id;
        this.name = name;
        this.cpf = cpf;
    }

}
