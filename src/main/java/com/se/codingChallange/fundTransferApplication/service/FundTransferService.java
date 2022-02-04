package com.se.codingChallange.fundTransferApplication.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.se.codingChallange.fundTransferApplication.dto.AccountDetails;
import com.se.codingChallange.fundTransferApplication.dto.CreateAccountRequest;
import com.se.codingChallange.fundTransferApplication.dto.FundTransferRequest;
import com.se.codingChallange.fundTransferApplication.dto.TransactionDetails;
import com.se.codingChallange.fundTransferApplication.entity.Account;
import com.se.codingChallange.fundTransferApplication.entity.Transaction;
import com.se.codingChallange.fundTransferApplication.exception.InsufficientFundsException;
import com.se.codingChallange.fundTransferApplication.mail.FundTransferEmailService;
import com.se.codingChallange.fundTransferApplication.mail.FundTransferMail;
import com.se.codingChallange.fundTransferApplication.repository.AccountRepository;
import com.se.codingChallange.fundTransferApplication.repository.TransactionRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class FundTransferService {
	
	@Autowired	
	private AccountRepository accountRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	
	
	
	/**
	 * @param accountRequest
	 */
	public void save(CreateAccountRequest accountRequest){
		log.info("Entering Save method of service");
		Account accountEntity = new Account();
		BeanUtils.copyProperties(accountRequest, accountEntity);
        accountRepository.save(accountEntity);
        log.info("Exiting Save method of service");
    }
	
	/**
	 * @return
	 */
	public List<CreateAccountRequest> findAll(){
		log.info("Entering findAll() method of service");
		List<CreateAccountRequest> accountRequest= new ArrayList<CreateAccountRequest>();
		List<Account> accountEntityList = accountRepository.findAll();
		Consumer<Account> accountConsumer = (Account accountEntity) -> {
			accountRequest.add(new CreateAccountRequest(accountEntity.getAccountNumber(),accountEntity.getCurrentBalance(), accountEntity.getEmail()));
        };
		accountEntityList.forEach(accountConsumer);
		log.info("Exiting findAll method of service");
		return accountRequest;
    }

    /**
     * @param accountNumber
     * @return
     */
    public Account findByAccountNumber(String accountNumber){
    	log.info("Entering findByAccountNumber method of service");
        Account account = accountRepository.findByAccountNumberEquals(accountNumber);
        log.info("Exiting findByAccountNumber method of service");
        return account;
    }


    
    /**
     * @param fundTransferRequest
     */
    public void sendMoney( FundTransferRequest fundTransferRequest ) {
    	log.info("Entering sendMoney method of service");
        String fromAccountNumber = fundTransferRequest.getFromAccountNumber();
        String toAccountNumber = fundTransferRequest.getToAccountNumber();
        BigDecimal amount = fundTransferRequest.getAmount();
        Account fromAccount = accountRepository.findByAccountNumberEquals(fromAccountNumber);
        Account toAccount = accountRepository.findByAccountNumberEquals(toAccountNumber);
        if(fromAccount.getCurrentBalance().compareTo(BigDecimal.ONE) == 1
                && fromAccount.getCurrentBalance().compareTo(amount) == 1
        ){
        	log.info("Started transfering the funds....");
            fromAccount.setCurrentBalance(fromAccount.getCurrentBalance().subtract(amount));
            accountRepository.save(fromAccount);
            toAccount.setCurrentBalance(toAccount.getCurrentBalance().add(amount));
            accountRepository.save(toAccount);
            transactionRepository.save(new Transaction(0L,UUID.randomUUID().toString(), fromAccountNumber,toAccountNumber,amount, new Timestamp(System.currentTimeMillis())));
            log.info("Completed transfering the funds...");
           // sendEmail(fromAccount, toAccount);
           
        }else {
        	log.error("Insufficient funds");
        	throw new InsufficientFundsException();
        }
        log.info("Exiting sendMoney method of service");
    }

	/**
	 * @param fromAccount
	 * @param toAccount
	 */
	private void sendEmail(Account fromAccount, Account toAccount) {
		log.info("Entering sendEmail method of service");
		FundTransferEmailService emailService = new FundTransferEmailService();
		

		FundTransferMail mail = new FundTransferMail();
		mail.setFrom(fromAccount.getEmail());
        mail.setTo(toAccount.getEmail());
        mail.setSubject("Transfer completed");
        mail.setContent("");
        emailService.sendSimpleMessage(mail);
        log.info("Exiting sendEmail method of service");
	}


    /**
     * @param accountNumber
     * @return
     */
    public AccountDetails getAllTransactions(String accountNumber) {	
    	log.info("Entering getAllTransactions() method of service");
    	//Get the account information to get balance of account.
        Account account = accountRepository.findByAccountNumberEquals(accountNumber);
        List <TransactionDetails> transactionDetail= new ArrayList<TransactionDetails>();
        // Retrieve all debit transactions of the account and save into them in request DTO.
        List<Transaction> debitTransactions= transactionRepository.findByFromAccountNumberEquals(accountNumber);
        debitTransactions.forEach(debitTransaction->{
        	transactionDetail.add(new TransactionDetails(debitTransaction.getToAccountNumber(), debitTransaction.getAmount(), BigDecimal.ZERO, debitTransaction.getTransactionReference(), debitTransaction.getTransactionDateTime()));
        });
        //retrieve all credit transactions of the account and save into them in request DTO.
        List<Transaction> creditTransactions= transactionRepository.findByToAccountNumberEquals(accountNumber);
        creditTransactions.forEach(creditTransaction->{
        	transactionDetail.add(new TransactionDetails(creditTransaction.getFromAccountNumber(), BigDecimal.ZERO, creditTransaction.getAmount(), creditTransaction.getTransactionReference(), creditTransaction.getTransactionDateTime()));
        });
        //List<Transaction> allTransaction = Stream.of(debitTransactions, creditTransactions).flatMap(Collection::stream).collect(Collectors.toList());
        
        log.info("Exiting getAllTransactions method of service");
        return new AccountDetails(account.getCurrentBalance(),accountNumber,transactionDetail);
    }
}
