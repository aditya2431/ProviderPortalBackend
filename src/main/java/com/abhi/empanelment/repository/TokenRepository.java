package com.abhi.empanelment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abhi.empanelment.model.Token;



@Repository
public interface TokenRepository extends JpaRepository<Token, Integer>{

	@Query(value = "select t.id, t.is_logout, t.token, t.user_name from token t left join provider_portal_master m on t.user_name=m.user_name where t.user_name=:username and t.is_Logout=false",nativeQuery = true)
	List<Token> findAllTokenByUserName(String username);
	
	Optional<Token> findByToken(String token);

//	void saveAll(com.abhi.empanelment.model.Token tokenModel);
}
