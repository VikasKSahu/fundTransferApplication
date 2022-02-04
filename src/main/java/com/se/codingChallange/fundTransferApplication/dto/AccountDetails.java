package com.se.codingChallange.fundTransferApplication.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountDetails {
	  BigDecimal currentBalance;
	  String forAccountNumber;
	  List<TransactionDetails> transactionHistory;
}
	