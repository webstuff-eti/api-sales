package com.tiagotibaes.domain.repository;

import com.tiagotibaes.domain.entity.DemandItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandItemsRepository extends JpaRepository<DemandItem, Integer> {
}
