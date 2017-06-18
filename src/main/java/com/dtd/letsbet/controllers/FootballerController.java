package com.dtd.letsbet.controllers;

import com.dtd.letsbet.model.ExternalData.Footballer;
import com.dtd.letsbet.model.ExternalData.Team;
import com.dtd.letsbet.repositories.ExternalRepositories.FootballerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal on 24.05.2017.
 */
@RestController
@RequestMapping("/footballer")
public class  FootballerController {

    @Autowired
    FootballerRepository footballerRepository;

    @RequestMapping("/save")
    public String process() {
        Footballer pazdan = new Footballer(1,"Michal Pazdan");
        Footballer lewandowski = new Footballer(2,"Robert Lewandowski");

        Team team = new Team(23,"Polska");

        List<Team> teams = new ArrayList<>();
        teams.add(team);

        pazdan.setTeams(teams);

        footballerRepository.save(pazdan);
        footballerRepository.save(lewandowski);

        return "Done";
    }

    @RequestMapping("/findAll")
    public String findAll() {
        String result = "<html>";
        for(Footballer footballer: footballerRepository.findAll()) {
            result += "<div>" + footballer.toString() + "</div>";
        }
        return result + "</html>";
    }

    @RequestMapping("/delete")
    public String deleteAll() {
        footballerRepository.deleteAll();
        return "Deleted";
    }

}
