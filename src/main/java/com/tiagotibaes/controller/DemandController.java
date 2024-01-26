package com.tiagotibaes.controller;


import com.tiagotibaes.controller.dto.response.DemandResponseDTO;
import com.tiagotibaes.controller.dto.resquest.DemandRequestDTO;
import com.tiagotibaes.service.DemandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("api/demands")
public class DemandController {

    @Autowired
    private DemandService demandService;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer saveDemand(@RequestBody DemandRequestDTO demandRequestDTO) {
        return demandService.createDemand(demandRequestDTO);
    }


    @GetMapping("/search/{id}")
    public DemandResponseDTO searchDemandById(@PathVariable Integer id){

        Optional<DemandResponseDTO> demandResponseDTO = demandService.getDemandById(id);

        return demandResponseDTO
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado")
                );
    }


}
