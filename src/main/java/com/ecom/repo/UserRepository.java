package com.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entities.Ent_User;

@Repository
public interface UserRepository extends JpaRepository<Ent_User, Long> {
	
	Ent_User findByUsername(String username);

}
