package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.EmployeeSkills;

public interface EmployeeSkillRepoWithJpa extends JpaRepository<EmployeeSkills, Integer> {

	@Query(value = "select * from hr1_emp_skill where skill_name=:name OR skill_experience=:experience",nativeQuery = true)
	List<EmployeeSkills> findBySkillNameorExp(@Param("name")String name,@Param("experience")String experience );
	
	@Query(nativeQuery = true,value ="select * from hr1_emp_skill where emp_id=:id OR approved_by=:approvedBy OR status=:status")
	List<EmployeeSkills>searchByValue(@Param("id")int id ,@Param("approvedBy") String approvedBy,@Param("status") String status);

	
}
