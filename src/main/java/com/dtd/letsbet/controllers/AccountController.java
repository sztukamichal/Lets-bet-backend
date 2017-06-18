package com.dtd.letsbet.controllers;

import com.dtd.letsbet.model.*;
import com.dtd.letsbet.repositories.AccountRepository;
import com.dtd.letsbet.repositories.AccountTypeRepository;
import com.dtd.letsbet.repositories.PersonRepository;
import com.dtd.letsbet.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/acount")
public class AccountController
{

    @Autowired
    AccountTypeRepository accountTypeRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    PersonRepository personRepository;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
    String dateInString = "31-08-1982";
    Date date = sdf.parse(dateInString);

    String dateInString2 = "31-08-2016";
    Date date2 = sdf.parse(dateInString2);

    String dateInString3 = "02-02-2017";
    Date date3 = sdf.parse(dateInString3);

    public AccountController() throws ParseException {
    }

// save Player account

    AccountType accountType = new AccountType("STANDARD");
    PlayerStatus playerStatus = new PlayerStatus("ACTIVE");

    @RequestMapping("/saveAccount")
    public String saveAccount() {
        Player player = new Player(accountType,playerStatus,"login1", date2, "1111@gmail.com", "12345678", date3, 1, date3, 0,1, "asdff1", 0);
        playerRepository.save(player);
        int accountId = player.getID();
        Person person = new Person(1,accountId, "Anna", "Nowak", date , "Polish", 1);
        personRepository.save(person);
        return "Account added";
    }

// find accounts/players

    @RequestMapping("/findAllPlayers")
    public String findAllPlayers() {
        String result = "<html>";
        for(Player player: playerRepository.findAll()) {
            result += "<div>" + player.toString() + "</div>";
        }
        return result + "</html>";
    }

    @RequestMapping("/findAllAccounts")
    public String findAllAccounts() {
        String result = "<html>";
        for(Account account: accountRepository.findAll()) {
            result += "<div>" + account.toString() + "</div>";
        }
        return result + "</html>";
    }

    // delete all players

    @RequestMapping("/deletePlayers")
    public String deletePlayers(){
        personRepository.deleteAll();
        playerRepository.deleteAll();
        return "Players deleted";
    }


// *****PERSON*****

// save person

    @RequestMapping("/savePerson")
    public String savePerson() {
        personRepository.save(new Person(1,1, "Anna", "Nowak", date , "Polish", 1));
        return "Done";
    }

    // find all persons

    @RequestMapping("/findAllPersons")
    public String findAllPersons() {
        String result = "<html>";
        for(Person p: personRepository.findAll()) {
            result += "<div>" + p.toString() + "</div>";
        }
        return result + "</html>";
    }

}
