package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AssociateToManager;

public interface AssociateToManagerRepo extends JpaRepository<AssociateToManager, Integer> {

	
	List<AssociateToManager> findByManagerId(int id);
}
