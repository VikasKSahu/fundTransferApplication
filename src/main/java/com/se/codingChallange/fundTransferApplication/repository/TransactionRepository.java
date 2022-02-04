package com.se.codingChallange.fundTransferApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.se.codingChallange.fundTransferApplication.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
   List<Transaction> findByFromAccountNumberEquals(String accountNumber);
   List<Transaction> findByToAccountNumberEquals(String accountNumber);

}