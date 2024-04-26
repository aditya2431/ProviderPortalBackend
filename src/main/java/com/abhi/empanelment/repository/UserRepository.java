package com.abhi.empanelment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhi.empanelment.model.User;

public interface UserRepository extends JpaRepository<User, String>{
	List<User> findByZmAdId(String ZM);

	List<User> findByAdId(String adId);
	List<User> findByAdIdAndLocation(String adId, String location);
}
