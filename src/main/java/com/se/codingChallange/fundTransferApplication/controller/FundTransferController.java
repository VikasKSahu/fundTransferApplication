package com.se.codingChallange.fundTransferApplication.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.se.codingChallange.fundTransferApplication.dto.CreateAccountRequest;
import com.se.codingChallange.fundTransferApplication.dto.FundTransferRequest;
import com.se.codingChallange.fundTransferApplication.service.FundTransferService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RequiredArgsConstructor
@Controller
@RequestMapping("/fundTransfer")
public class FundTransferController {
	@Autowired private final FundTransferService fundTransferService;
	
	/**
	 * @return
	 */
	@GetMapping("/")
    public String welcomePage() {
		log.info("Index page");
        return "index";
    }
	 
	/**
	 * @param model
	 * @return
	 */
	@GetMapping("/accountForm")
    public String showAccountForm(Model model) {
		log.info("showAccountForm");
	 	model.addAttribute("createAccountRequest", new CreateAccountRequest());   
        return "accountform";
    }
	
    /**
     * @param account
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping(value="/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public String create(@ModelAttribute("account")@Valid  CreateAccountRequest account, BindingResult bindingResult,ModelMap model) {
    	log.info("create account method ");
	   if (bindingResult.hasErrors()) {
		return "accountform";
       }
       fundTransferService.save(account);
       model.put("accounts",fundTransferService.findAll());
       return "accountsdetail";
    }

    
    /**
     * @param model
     * @return
     */
    @GetMapping("/viewAllAccounts")
    public String all(Model model) {
    	List <CreateAccountRequest> accounts = fundTransferService.findAll();
    	model.addAttribute("accounts", accounts);
    	    
        return "accountsdetail";
    }

    /**
     * @param model
     * @return
     */
    @GetMapping("/transferfund")
    public String showTransferFundForm(Model model) {
    	model.addAttribute("accounts", getAllAccountNumbers());	
    	model.addAttribute("fundTransferRequest", new FundTransferRequest());
        return "transferfund";
    }

	/**
	 * @return
	 */
	private List<String> getAllAccountNumbers() {
		List <CreateAccountRequest> accounts = fundTransferService.findAll();
    	List <String> accountNumbers= new ArrayList<String>();
    	accounts.forEach(account->accountNumbers.add(account.getAccountNumber()));
		return accountNumbers;
	}
    /**
     * @param fundTransferRequest
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping(value="/sendmoney",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String sendMoney(@Valid FundTransferRequest fundTransferRequest,BindingResult bindingResult, ModelMap model  ) {
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("accounts", getAllAccountNumbers());	
            model.addAttribute("fundTransferRequest", new FundTransferRequest());
    	    return "transferfund";
        }
    	fundTransferService.sendMoney(fundTransferRequest);
    	model.put("accounts",fundTransferService.findAll());
        return "accountsdetail";
   }
    
    
    /**
     * @param accountNumber
     * @return
     */
    @GetMapping(value="/transaction" )
    public ModelAndView getAllTransactions( @RequestParam(name="accountNumber", required = false)String accountNumber ){
    	ModelAndView modelView= new ModelAndView();
    	modelView.addObject("transactions",fundTransferService.getAllTransactions(accountNumber));
    	modelView.setStatus(HttpStatus.OK); 
    	modelView.setViewName("transactions");
        return modelView;

    }

}
