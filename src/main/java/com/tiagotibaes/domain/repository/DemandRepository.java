package com.tiagotibaes.domain.repository;

import com.tiagotibaes.domain.entity.Client;
import com.tiagotibaes.domain.entity.Demand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandRepository extends JpaRepository <Demand, Integer>{

    //FIXME:Pedidos de um cliente
    List<Demand> findByClient(Client client);

}
