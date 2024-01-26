package com.tiagotibaes.service.impl;


import com.tiagotibaes.controller.dto.response.ClientResponseDTO;
import com.tiagotibaes.controller.dto.resquest.ClientRequestDTO;
import com.tiagotibaes.domain.entity.Client;
import com.tiagotibaes.domain.entity.Document;
import com.tiagotibaes.domain.enumerated.TypeDocument;
import com.tiagotibaes.domain.repository.ClientRepository;
import com.tiagotibaes.domain.repository.DocumentRepository;
import com.tiagotibaes.exception.BusinessRuleException;
import com.tiagotibaes.service.ClientService;

import com.tiagotibaes.utils.converters.ConverterObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private ConverterObjects converterObjects;


    @Override
    @Transactional
    public ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO) {

        Client client = Client.builder()
                              .includedDate(LocalDate.now())
                              .updateDate(LocalDate.now())
                              .name(clientRequestDTO.getName())
                              .birthDate(LocalDate.parse(clientRequestDTO.getBirthDate()))
                              .build();

        Client clientSaved = clientRepository.save(client);
        Set<Document> documentList = this.converterDocuments(clientSaved, clientRequestDTO);

        documentRepository.saveAll(documentList);
        client.setDocuments(documentList);

        return converterObjects.converterObjectToObject(client, ClientResponseDTO.class);
    }

    private Set<Document> converterDocuments(Client clientSaved, ClientRequestDTO clientRequestDTO){
        if(clientRequestDTO.getDocuments().isEmpty()){
            throw new BusinessRuleException("Não é permitido cadastrar cliente sem ao menos um documento");
        }



        return clientRequestDTO.getDocuments()
                .stream()
                .map( dto -> {

                    Document document = new Document();

                    if(TypeDocument.CNH.getValue().equals(dto.getTypeDocument())){
                        document.setTypeDocument(TypeDocument.CNH);
                    }else if(TypeDocument.RG.getValue().equals(dto.getTypeDocument())){
                        document.setTypeDocument(TypeDocument.RG);
                    }else if(TypeDocument.CNPJ.getValue().equals(dto.getTypeDocument())){
                        document.setTypeDocument(TypeDocument.CNPJ);
                    }else if(TypeDocument.CPF.getValue().equals(dto.getTypeDocument())){
                        document.setTypeDocument(TypeDocument.CPF);
                    }

                    document.setClient(clientSaved);
                    document.setIncludedDate(LocalDate.now());
                    document.setUpdateDate(LocalDate.now());
                    document.setIdentificationNumber(dto.getIdentificationNumber());

                    return document;
                }).collect(Collectors.toSet());

    }

    @Override
    public Optional<ClientResponseDTO> getClientById(Integer id) {
        Optional<Client> client = clientRepository.findById(id);
        return Optional.of(converterObjects.converterObjectToObject(client.get(), ClientResponseDTO.class));
    }

    @Override
    public boolean deleteClientById(Integer id) {
       Optional<Client> existClient =  clientRepository.findById(id);
       if(existClient.isPresent()){
           clientRepository.deleteById(id);
           return true;
       }
       return false;
    }

    @Override
    public Client updateClientById(Integer id, Client client) {
        Optional<Client> existClient =  clientRepository.findById(id);
        Client modifiedClient = new Client();
        if(existClient.isPresent()){
            client.setId(id);
            modifiedClient =  clientRepository.save(client);
        }
        return modifiedClient;
    }

    @Override
    public List<Client> findListClientsByFilter(Client filterClient) {

        ExampleMatcher matcher =
                ExampleMatcher.matching()
                              .withIgnoreCase()
                              .withStringMatcher(
                                    ExampleMatcher.StringMatcher.CONTAINING);

        Example exampleFilterClient = Example.of(filterClient, matcher);
        List<Client> clients = clientRepository.findAll(exampleFilterClient);
        return clients;
    }


}
