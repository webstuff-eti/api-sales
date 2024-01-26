package com.tiagotibaes.domain.repository;

import com.tiagotibaes.domain.entity.Client;
import com.tiagotibaes.domain.entity.Demand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DemandRepository extends JpaRepository<Demand, Integer>{

    //FIXME:Pedidos de um cliente
    List<Demand> findByClient(Client client);

    @Query(" select d from Demand d left join fetch d.demandItems where d.id =:id ")
    Optional<Demand> findByIdFetchDemandItems(@Param("id") Integer id);
}
