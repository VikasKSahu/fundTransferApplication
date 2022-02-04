package com.se.codingChallange.fundTransferApplication.entity;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue	
    private Long accountId;
    @Column(unique=true)
    @NotNull(message = "From Account Number is mandatory.")
    String accountNumber;
    
    @NotNull(message = "Current balance is mandatory.")
    @DecimalMin(value= "200.0", message = "Minimum account balance is 200.")
    BigDecimal currentBalance;
    @Email
    String email;
}
