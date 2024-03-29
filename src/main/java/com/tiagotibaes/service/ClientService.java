package com.tiagotibaes.service;

import com.tiagotibaes.controller.dto.response.ClientResponseDTO;
import com.tiagotibaes.controller.dto.resquest.ClientRequestDTO;
import com.tiagotibaes.domain.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    public ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO);
    public Optional<ClientResponseDTO> getClientById(Integer id);

    public boolean deleteClientById(Integer id);

    public Client updateClientById(Integer id, Client client);

    public List<Client> findListClientsByFilter(Client client);
}
