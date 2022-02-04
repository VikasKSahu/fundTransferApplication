package com.se.codingChallange.fundTransferApplication.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "transaction")
public class Transaction {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	     String transactionReference;
	     @NotBlank(message = "From Account Number is mandatory")
	     String fromAccountNumber;
	     @NotBlank(message = "To Account Number is mandatory")
	     String toAccountNumber;
	     
	     BigDecimal amount;
	     Timestamp transactionDateTime;

}
