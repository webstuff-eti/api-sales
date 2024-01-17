package com.tiagotibaes.domain.repository;

import com.tiagotibaes.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByNameLike(String name);

    List<Client> findByIdOrName(Integer id, String name);

    List<Client> findByNameOrIdOrderById(Integer id, String name);

    boolean existsByName(String name);

    //FIXME: HQL

//    @Query(value = " select c from Client c where c.name like '%:name%' ", nativeQuery = true")
    @Query(value = " select c from Client c where c.name like :name ")
    List<Client> searchClientsByNameWithHQL(@Param("name") String name);

    @Query(value = " select * from Client c where c.name like '%:name%' ", nativeQuery = true)
    List<Client> searchClientsByNameWithQueryNative(@Param("name") String name);

    void deleteByName(String nome);

    /*
    @Query(" delete from client c where c.name =:name ")
    @Modifying
    void deleteByName(String nome);
    */

    @Query(" select c from Client c left join fetch c.demands where c.id = :id ")
    Client findClientFetchDemand(@Param("id") Integer id);

}
