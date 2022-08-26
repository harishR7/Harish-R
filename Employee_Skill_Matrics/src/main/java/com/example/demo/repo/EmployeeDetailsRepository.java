package com.example.demo.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;

import com.example.demo.entity.EmployeeDesignation;
import com.example.demo.entity.EmployeeDetails;


public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails,Integer>{

	
	@Query(value ="update hr1_emp_details set emp_name=:empName,emp_doj=:empDoj,emp_designation_id=:empDesignationId  where emp_id=:empId",nativeQuery = true)
	@Modifying
	@Transactional
	int updateEmployeeDetails(@Param("empName")String empName,@Param("empDoj")LocalDate empDoj,@Param("empDesignationId") int empDesignationId,@Param("empId") int empid); 
	
	List<EmployeeDetails> findByEmployeeDesignationId(int employeeDesignationId);
	List<EmployeeDetails> findByEmployeeName(String name);
}
