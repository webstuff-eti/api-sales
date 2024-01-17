package com.tiagotibaes.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "demand_item")
public class DemandItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    /*
    Relacionamento: Muitos itens pedidos para um pedido
     */
    @ManyToOne
    @JoinColumn(name = "demand_id")
    private Demand demand;

    /*
    Relacionamento: Muitos itens de um pedido para um produto
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;
}
