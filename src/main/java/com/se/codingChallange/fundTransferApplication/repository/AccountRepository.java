package com.se.codingChallange.fundTransferApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.se.codingChallange.fundTransferApplication.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumberEquals(String accountNumber);

}
