package com.abhi.empanelment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.abhi.empanelment.model.MOUDocument;

public interface MOUDocumentRepository extends JpaRepository<MOUDocument, String>{
}