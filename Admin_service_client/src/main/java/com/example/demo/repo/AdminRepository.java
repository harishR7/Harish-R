package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {


}
