package com.tiagotibaes.controller;


import com.tiagotibaes.controller.dto.resquest.ClientRequestDTO;
import com.tiagotibaes.domain.entity.Client;
import com.tiagotibaes.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping("/{id}")
    public Client searchClientById(@PathVariable Integer id) throws ResponseStatusException {
        Optional<Client> clientId = clientService.getClientById(id);

        return clientId
                .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado")
                );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer saveClient(@RequestBody ClientRequestDTO clientRequestDTO) {
        return clientService.createClient(clientRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Integer id){
        boolean clientWasDeleted = clientService.deleteClientById(id);
        if (!clientWasDeleted){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não existe!");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCliente(@PathVariable Integer id, @RequestBody Client client){
        Client update = clientService.updateClientById(id, client);
        if(update == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não existe!");
        }
    }


    @GetMapping("/filter")
    public List<Client> searchClientByFilter(Client filter) {
        return clientService.findListClientsByFilter(filter);
    }



}
