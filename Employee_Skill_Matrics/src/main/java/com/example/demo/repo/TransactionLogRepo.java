package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TransactionLog;

public interface TransactionLogRepo extends JpaRepository<TransactionLog, Integer> {

}
