package com.dtd.letsbet.controllers;

import com.dtd.letsbet.model.AccountType;
import com.dtd.letsbet.repositories.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounttype")
public class AccountTypeController {

    @Autowired
    AccountTypeRepository accountTypeRepository;


    //*****Account types*****

// find account types

    @RequestMapping("/findAllAccountTypes")
    public String findAllAccountTypes() {
        String result = "<html>";
        for(AccountType accountType: accountTypeRepository.findAll()) {
            result += "<div>" + accountType.toString() + "</div>";
        }
        return result + "</html>";
    }
}
