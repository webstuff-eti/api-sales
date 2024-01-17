package com.tiagotibaes.service.impl;

import com.tiagotibaes.controller.dto.resquest.ClientRequestDTO;
import com.tiagotibaes.controller.dto.resquest.DocumentRequestDTO;
import com.tiagotibaes.domain.entity.Client;
import com.tiagotibaes.domain.entity.Document;
import com.tiagotibaes.domain.enumerated.TypeDocument;
import com.tiagotibaes.domain.repository.ClientRepository;
import com.tiagotibaes.domain.repository.DocumentRepository;
import com.tiagotibaes.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DocumentRepository documentRepository;


    @Override
    @Transactional
    public Integer createClient(ClientRequestDTO dto) {

        Client client = Client.builder()
                              .includedDate(LocalDate.now())
                              .updateDate(LocalDate.now())
                              .name(dto.getName())
                              .birthDate(LocalDate.parse(dto.getBirthDate()))
                              .build();

        Client clientSaved = clientRepository.save(client);

        Document document = new Document();
        List<Document> documents = new ArrayList<>();

        List<DocumentRequestDTO> documentRequestDTOS = dto.getDocuments();

        for (DocumentRequestDTO d: documentRequestDTOS) {

            if(d != null){
                documents.add(document);
            }

            if(TypeDocument.CNH.getValue().equals(d.getTypeDocument())){
                document.setTypeDocument(TypeDocument.CNH);
            }else if(TypeDocument.RG.getValue().equals(d.getTypeDocument())){
                document.setTypeDocument(TypeDocument.RG);
            }else if(TypeDocument.CNPJ.getValue().equals(d.getTypeDocument())){
                document.setTypeDocument(TypeDocument.CNPJ);
            }else if(TypeDocument.CPF.getValue().equals(d.getTypeDocument())){
                document.setTypeDocument(TypeDocument.CPF);
            }

            document.setClient(clientSaved);
            document.setIncludedDate(LocalDate.now());
            document.setUpdateDate(LocalDate.now());
            document.setIdentificationNumber(d.getIdentificationNumber());


        }

        /*
        dto.getDocuments().stream().forEach( d -> {

            if(TypeDocument.CNH.getValue().equals(d.getTypeDocument())){
                document.setTypeDocument(TypeDocument.CNH);
            }else if(TypeDocument.RG.getValue().equals(d.getTypeDocument())){
                document.setTypeDocument(TypeDocument.RG);
            }else if(TypeDocument.CNPJ.getValue().equals(d.getTypeDocument())){
                document.setTypeDocument(TypeDocument.CNPJ);
            }else if(TypeDocument.CPF.getValue().equals(d.getTypeDocument())){
                document.setTypeDocument(TypeDocument.CPF);
            }

            document.setClient(clientSaved);
            document.setIncludedDate(LocalDate.now());
            document.setUpdateDate(LocalDate.now());
            document.setIdentificationNumber(d.getIdentificationNumber());

            documents.add(document);
        });
        */

        documentRepository.saveAll(documents);

        client.setDocuments(documents);

        return client.getId();
    }

    @Override
    public Optional<Client> getClientById(Integer id) {
        return clientRepository.findById(id);
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
