package com.abhi.empanelment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abhi.empanelment.model.Pincode;

public interface PincodeRepository extends JpaRepository<Pincode, Integer>{

	Pincode findByPinCode(String pincode);
	 @Query("SELECT p.pinCode FROM Pincode p WHERE p.pinCode LIKE %:partialPincode%")
	    List<String> findSuggestionsByPartialPincode(String partialPincode);
}