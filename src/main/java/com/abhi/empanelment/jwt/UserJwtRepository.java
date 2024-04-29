package com.abhi.empanelment.jwt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abhi.empanelment.jwt.UserJwtLogin;

@Repository
public interface UserJwtRepository extends JpaRepository<UserJwtLogin, String> {

	@Query(value="select * from provider_portal_master where user_name=:name",nativeQuery = true)
	UserJwtLogin authenicateUserName(String name);
	
}
