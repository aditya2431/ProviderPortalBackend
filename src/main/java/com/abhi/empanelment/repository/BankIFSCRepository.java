package com.abhi.empanelment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.abhi.empanelment.model.BankIFSC;

public interface BankIFSCRepository extends JpaRepository<BankIFSC, Integer>{

	BankIFSC findByIfscCode(String ifscCode);
}