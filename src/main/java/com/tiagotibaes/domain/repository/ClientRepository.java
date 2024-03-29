package com.tiagotibaes.domain.repository;

import com.tiagotibaes.domain.entity.Client;
import com.tiagotibaes.domain.entity.Demand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByNameLike(String name);

    List<Client> findByIdOrName(Integer id, String name);

    List<Client> findByNameOrIdOrderById(Integer id, String name);

    boolean existsByName(String name);

    @Query(value = " select c from Client c where c.name like :name ")
    List<Client> searchClientsByNameWithHQL(@Param("name") String name);

    @Query(value = " select * from Client c where c.name like '%:name%' ", nativeQuery = true)
    List<Client> searchClientsByNameWithQueryNative(@Param("name") String name);

    void deleteByName(String nome);

    @Query(" select c from Client c left join fetch c.documents where c.id =:id ")
    Client findClientFetchDocument(@Param("id") Integer id);

    //TODO: iMPLEMENTAR BUSCA DO CLIENTE PELO ID DO PEDIDO
    @Query(" select c from Client c left join fetch c.demands where c.id =:id ")
    Optional<Client> findClientFetchDemand(@Param("id") Integer id);

}
