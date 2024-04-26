package com.abhi.empanelment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abhi.empanelment.model.FileDB;
import com.abhi.empanelment.model.SendLink;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
	Optional<FileDB> findByWorkflowNoAndDocID(String workflowNo, String docID);

	List<FileDB> findByWorkflowNo(String workflowNo);
	
	@Modifying
	@Query("update FileDB set data = :fileData, name = :fileName, type = :fileContentType where workflowNo = :workflowNumber and docId = :documentID")
	void updateData(byte[] fileData, String fileName, String fileContentType, String workflowNumber, String documentID);

}
