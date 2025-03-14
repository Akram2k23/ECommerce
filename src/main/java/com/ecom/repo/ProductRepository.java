package com.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entities.Ent_Product;

@Repository
public interface ProductRepository extends JpaRepository<Ent_Product, Long> {

}
