package com.se.codingChallange.fundTransferApplication.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FundTransferMail {
	private String from;
    private String to;
    private String subject;
    private String content;  
}
