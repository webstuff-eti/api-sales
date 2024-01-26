package com.tiagotibaes.service.impl;


import com.tiagotibaes.controller.dto.response.*;
import com.tiagotibaes.controller.dto.resquest.DemandItemRequestDTO;
import com.tiagotibaes.controller.dto.resquest.DemandRequestDTO;
import com.tiagotibaes.domain.entity.*;
import com.tiagotibaes.domain.repository.*;
import com.tiagotibaes.exception.BusinessRuleException;
import com.tiagotibaes.service.DemandService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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


    private List<DemandItem> converterItems(Demand demand, List<DemandItemRequestDTO> itemsDTO) {

        if (itemsDTO.isEmpty()) {
            throw new BusinessRuleException("Não é permitido realizar um pedido sem items");
        }

        return itemsDTO
                .stream()
                .map(dto -> {
                    Integer idProduct = dto.getProductId();
                    Product product = productRepository
                            .findById(idProduct)
                            .orElseThrow(
                                    () -> new BusinessRuleException(
                                            "Código de produto inválido: " + idProduct
                                    ));

                    DemandItem demandItem = new DemandItem();

                    demandItem.setQuantity(dto.getQuantity());
                    demandItem.setDemand(demand);
                    demandItem.setProduct(product);

                    return demandItem;
                }).collect(Collectors.toList());

    }


    @Override
    public Optional<DemandResponseDTO> getDemandById(Integer idDemand) {

        Optional<Demand> demand = demandRepository.findByIdFetchDemandItems(idDemand);
        DemandResponseDTO responseDTO = new DemandResponseDTO();

        responseDTO = demand.map(d -> converterDemand(d))
                .orElseThrow(() -> new BusinessRuleException(
                        "Código de pedido inválido: " + idDemand
                ));

        return Optional.ofNullable(responseDTO);
    }


    private DemandResponseDTO converterDemand(Demand demand) {

        return DemandResponseDTO.builder()
                .idDemand(demand.getId())
                .dataDemand(demand.getDataDemand())
                .documents(this.setConverterDocuments(demand.getClient().getDocuments()))
                .nameClient(demand.getClient().getName())
                .total(demand.getTotal())
                .items(this.converterListDemand(demand.getDemandItems()))
                .build();

    }


    private List<DocumentResponseDTO> setConverterDocuments(Set<Document> documents) {

        return documents.stream().map(d -> {

            DocumentResponseDTO responseDTO = new DocumentResponseDTO();
            responseDTO.setIdentificationNumber(d.getIdentificationNumber());
            responseDTO.setTypeDocument(d.getTypeDocument());

            return responseDTO;
        }).collect(Collectors.toList());

    }


    private List<DemandItemResponseDTO> converterListDemand(List<DemandItem> demandItems) {

        if (CollectionUtils.isEmpty(demandItems)) {
            return Collections.emptyList();
        }

        return demandItems.stream().map(demandItem ->

                DemandItemResponseDTO.builder()
                        .productDescription(demandItem.getProduct().getDescription())
                        .productUnitPrice(demandItem.getProduct().getUnitPrice())
                        .quantityOfProducts(demandItem.getQuantity())
                        .build()
        ).collect(Collectors.toList());

    }

    //TODO: Concluir implementação
    @Override
    public boolean deleteDemandById(Integer id) {
        return false;
    }

    //TODO: Concluir implementação
    @Override
    public Demand updateDemandById(Integer id, DemandRequestDTO demandRequestDTO) {
        return null;
    }

    //TODO: Concluir implementação
    @Override
    public List<Demand> findListDemandsByFilter(DemandRequestDTO demandRequestDTO) {
        return null;
    }


}
