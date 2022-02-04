package com.se.codingChallange.fundTransferApplication.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FundTransferRequest {
		
		   String fromAccountNumber;
		   String toAccountNumber;
		   BigDecimal amount;
       
}
