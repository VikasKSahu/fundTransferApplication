package com.se.codingChallange.fundTransferApplication;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.se.codingChallange.fundTransferApplication.dto.CreateAccountRequest;
import com.se.codingChallange.fundTransferApplication.dto.FundTransferRequest;
import com.se.codingChallange.fundTransferApplication.service.FundTransferService;

@SpringBootTest
class FundTransferApplicationTests {

	@Test
	void contextLoads(	) {
		
	}

	@TestConfiguration
    static class FundTransferApplicationTestsContextConfiguration {
        @Bean
        public FundTransferApplication FundTransferApplicationTests() {
            return new FundTransferApplication();

        }
    }
	
	@Autowired private  FundTransferService fundTransferService;
	
	@Test
    public void sendMoneyTest() {
        CreateAccountRequest account1 = new CreateAccountRequest("1001", new BigDecimal(50000),"a@test.com");
        CreateAccountRequest account2 = new CreateAccountRequest("1002", new BigDecimal(50000),"a@test.com");
        fundTransferService.save(account1);
        fundTransferService.save(account2);

        FundTransferRequest fundTransferReq =
                new FundTransferRequest(
                        account1.getAccountNumber(),
                        account2.getAccountNumber(),
                        new BigDecimal(3000)
                );
        fundTransferService.sendMoney(fundTransferReq);
        assertTrue(fundTransferService.findByAccountNumber(account1.getAccountNumber())
                .getCurrentBalance().compareTo(new BigDecimal(47000))==0);
        assertTrue(fundTransferService.findByAccountNumber(account1.getAccountNumber())
                .getCurrentBalance().compareTo(new BigDecimal(47000))==0);

    }
	
	 @Test
	    public void getStatement() {
		 CreateAccountRequest account1 = new CreateAccountRequest("1003", new BigDecimal(50000),"a@test.com");
	        CreateAccountRequest account2 = new CreateAccountRequest("1004", new BigDecimal(50000),"a@test.com");
	        fundTransferService.save(account1);
	        fundTransferService.save(account2);
	        FundTransferRequest fundTransferReq =
	                new FundTransferRequest(
	                        account1.getAccountNumber(),
	                        account2.getAccountNumber(),
	                        new BigDecimal(3000)
	                );
	        fundTransferService.sendMoney(fundTransferReq);
	        assertTrue(fundTransferService.getAllTransactions(account1.getAccountNumber())
	                .getCurrentBalance()
	                .compareTo(new BigDecimal(47000))==0);
	        fundTransferService.sendMoney(fundTransferReq);
	        assertTrue(fundTransferService.getAllTransactions(account1.getAccountNumber())
	                .getCurrentBalance().compareTo(new BigDecimal(44000))==0);
	        assertTrue(fundTransferService.getAllTransactions(account1.getAccountNumber())
	                .getTransactionHistory().size()==2);
	    }

}
