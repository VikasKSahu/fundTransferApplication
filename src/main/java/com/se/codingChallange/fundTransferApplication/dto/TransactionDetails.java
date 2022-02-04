package com.se.codingChallange.fundTransferApplication.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data	
public class TransactionDetails {
  
	String accountNumber;
   
    BigDecimal debitAmount;
    
    BigDecimal creditAmount;
    
    String transactionReference;
    
    Timestamp transactionDateTime;
}
