package com.tiagotibaes.service;

import com.tiagotibaes.controller.dto.response.DemandResponseDTO;
import com.tiagotibaes.controller.dto.resquest.DemandRequestDTO;
import com.tiagotibaes.domain.entity.Demand;

import java.util.List;
import java.util.Optional;

public interface DemandService {

    public Integer createDemand(DemandRequestDTO dto);
    public Optional<Demand> getDemandById(Integer id);

    public boolean deleteDemandById(Integer id);

    public Demand updateDemandById(Integer id, DemandRequestDTO demandRequestDTO);

    public List<Demand> findListDemandsByFilter(DemandRequestDTO demandRequestDTO);
}
