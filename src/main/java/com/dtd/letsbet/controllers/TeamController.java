package com.dtd.letsbet.controllers;

import com.dtd.letsbet.model.ExternalData.Footballer;
import com.dtd.letsbet.model.ExternalData.Team;
import com.dtd.letsbet.repositories.ExternalRepositories.FootballerRepository;
import com.dtd.letsbet.repositories.ExternalRepositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal on 24.05.2017.
 */
@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    FootballerRepository footballerRepository;

    @RequestMapping("/save")
    public String process() {
        Footballer pazdan = footballerRepository.findOneByName("Michal Pazdan");
        Footballer lewandowski = footballerRepository.findOneByName("Robert Lewandowski");

        List<Footballer> footballers = new ArrayList<>();
        footballers.add(new Footballer(23,"Test"));
        footballers.add(new Footballer(22,"Tes222t"));
        footballers.add(pazdan);
        footballers.add(lewandowski);
        if(lewandowski != null)
            System.out.println(lewandowski.getName());
        else
            System.out.println("Nie ma lewego :(");

        Team team = new Team(1, "Polska");
        team.setFootballers(footballers);

        teamRepository.save(team);

        return "Done";
    }

    @RequestMapping("/findAll")
    public String findAll() {
        String result = "<html>";
        for(Team team: teamRepository.findAll()) {
            result += "<div>" + team.toString() + "</div>";
        }
        return result + "</html>";
    }

    @RequestMapping("/delete")
    public String deleteAll() {
        teamRepository.deleteAll();
        return "Deleted";
    }

}
