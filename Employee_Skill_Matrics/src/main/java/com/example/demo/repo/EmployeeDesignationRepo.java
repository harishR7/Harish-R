package com.example.demo.repo;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.EmployeeDesignation;

public interface EmployeeDesignationRepo extends JpaRepository<EmployeeDesignation, Integer> {

	EmployeeDesignation findByDesignation(String name);
}
