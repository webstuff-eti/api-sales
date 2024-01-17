package com.tiagotibaes.service.impl;

import com.tiagotibaes.domain.entity.Client;
import com.tiagotibaes.domain.repository.ClientRepository;
import com.tiagotibaes.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
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
