package com.se.codingChallange.fundTransferApplication.dto;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;	
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor		
@Data
public class CreateAccountRequest {
  
    @Column(unique=true)
    @NotNull(message = "From Account Number is mandatory.")
    String accountNumber;
    
    @NotNull(message = "Current balance is mandatory.")
    @DecimalMin(value= "200.0", message = "Minimum account balance is 200.")
    BigDecimal currentBalance;
    @NotNull @Email
    String email;
}
