package com.tiagotibaes.controller;


import com.tiagotibaes.controller.dto.response.DemandResponseDTO;
import com.tiagotibaes.controller.dto.resquest.DemandRequestDTO;
import com.tiagotibaes.domain.entity.Client;
import com.tiagotibaes.domain.entity.Demand;
import com.tiagotibaes.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
