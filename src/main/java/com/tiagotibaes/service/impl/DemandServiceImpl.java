package com.tiagotibaes.service.impl;


import com.tiagotibaes.controller.dto.resquest.DemandItemRequestDTO;
import com.tiagotibaes.controller.dto.resquest.DemandRequestDTO;
import com.tiagotibaes.domain.entity.Client;
import com.tiagotibaes.domain.entity.Demand;
import com.tiagotibaes.domain.entity.DemandItem;
import com.tiagotibaes.domain.entity.Product;
import com.tiagotibaes.domain.repository.ClientRepository;
import com.tiagotibaes.domain.repository.DemandItemsRepository;
import com.tiagotibaes.domain.repository.DemandRepository;
import com.tiagotibaes.domain.repository.ProductRepository;
import com.tiagotibaes.exception.BusinessRuleException;
import com.tiagotibaes.service.DemandService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DemandServiceImpl implements DemandService {

    private final DemandRepository demandRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final DemandItemsRepository demandItemsRepository;

    @Override
    @Transactional
    public Integer createDemand(DemandRequestDTO dto) {

        Integer idClient = dto.getClientId();

        Client client = clientRepository
                .findById(idClient)
                .orElseThrow(
                        () -> new BusinessRuleException("Id de cliente inválido.")
                );

        Demand serachDemand = Demand.builder()
                .client(client)
                .total(dto.getTotal())
                .dataDemand(LocalDate.now())
                .build();

        List<DemandItem> demandItems = converterItems(serachDemand, dto.getItems());
        Demand demandSaved = demandRepository.save(serachDemand);
        List<DemandItem> demandItemsSaved = demandItemsRepository.saveAll(demandItems);
        serachDemand.setDemandItems(demandItemsSaved);

        return serachDemand.getId();
    }


    private List<DemandItem> converterItems(Demand demand, List<DemandItemRequestDTO> itemsDTO){

        if(itemsDTO.isEmpty()){
            throw new BusinessRuleException("Não é permitido realizar um pedido sem items");
        }

        return itemsDTO
                .stream()
                .map( dto -> {
                    Integer idProduct = dto.getProductId();
                    Product product = productRepository
                            .findById(idProduct)
                            .orElseThrow(
                                    () -> new BusinessRuleException(
                                            "Código de produto inválido: "+ idProduct
                                    ));

                    DemandItem demandItem = new DemandItem();

                    demandItem.setQuantity(dto.getQuantity());
                    demandItem.setDemand(demand);
                    demandItem.setProduct(product);

                    return demandItem;
                }).collect(Collectors.toList());

    }


    @Override
    public Optional<Demand> getDemandById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteDemandById(Integer id) {
        return false;
    }

    @Override
    public Demand updateDemandById(Integer id, DemandRequestDTO demandRequestDTO) {
        return null;
    }

    @Override
    public List<Demand> findListDemandsByFilter(DemandRequestDTO demandRequestDTO) {
        return null;
    }

}
