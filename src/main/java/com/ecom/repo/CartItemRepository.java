package com.ecom.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entities.Ent_Cart;
import com.ecom.entities.Ent_CartItem;

public interface CartItemRepository extends JpaRepository<Ent_CartItem, Long> {
	
	Ent_CartItem findByProductProIdAndCartId(long proId, long id);

}
