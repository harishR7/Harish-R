package com.example.demo.repo;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.SkillReference;

public interface SkillReferenceRepo extends JpaRepository<SkillReference, Integer> {

	Optional<SkillReference> findBySkillName(String skillName);
	
	

}
