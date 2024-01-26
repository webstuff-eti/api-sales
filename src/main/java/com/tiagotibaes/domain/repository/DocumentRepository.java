package com.tiagotibaes.domain.repository;

import com.tiagotibaes.domain.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Integer> {


}
