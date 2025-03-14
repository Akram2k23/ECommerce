package com.ecom.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entities.Ent_Cart;

public interface CartRepository extends JpaRepository<Ent_Cart, Long> {
	
	Optional<Ent_Cart> findByUserId(long userId);

}
