package com.dtd.letsbet.controllers;

import com.dtd.letsbet.model.BetStatus;
import com.dtd.letsbet.repositories.BetStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Michal on 24.05.2017.
 */
@RestController
public class BetStatusController {

    @Autowired
    BetStatusRepository betStatusRepository;

    @RequestMapping("/save")
    public String process() {
        betStatusRepository.save(new BetStatus("OLD"));
        betStatusRepository.save(new BetStatus("FINISHED"));
        betStatusRepository.save(new BetStatus("ERROR"));
        return "Done";
    }

    @RequestMapping("/findAll")
    public String findAll() {
        String result = "<html>";
        for(BetStatus betStatus: betStatusRepository.findAll()) {
            result += "<div>" + betStatus.toString() + "</div>";
        }
        return result + "</html>";
    }

    @RequestMapping("/delete")
    public String deleteAll() {
        betStatusRepository.deleteAll();
        return "Deleted";
    }

}
