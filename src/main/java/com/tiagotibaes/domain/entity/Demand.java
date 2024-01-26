package com.tiagotibaes.domain.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "demand")
public class Demand implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    /*
        Relacionamento: Muitos Pedidos para um Cliente
    */
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "data_demand")
    private LocalDate dataDemand;

    @Column(name = "total", precision = 20, scale = 2)
    private BigDecimal total;

    //Listar os itens do pedido
    @OneToMany(mappedBy = "demand")
    private List<DemandItem> demandItems;

}
