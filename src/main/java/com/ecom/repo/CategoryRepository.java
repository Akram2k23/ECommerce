package com.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entities.Ent_Category;

public interface CategoryRepository extends JpaRepository<Ent_Category, Long> {

}
