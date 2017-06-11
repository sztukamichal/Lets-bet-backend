package com.dtd.letsbet.controllers;

import com.dtd.letsbet.model.ExternalData.*;
import com.dtd.letsbet.repositories.ExternalRepositories.CompetitionRepository;
import com.dtd.letsbet.repositories.ExternalRepositories.LeagueTableRepository;
import com.dtd.letsbet.repositories.ExternalRepositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal on 24.05.2017.
 */
@RestController
@RequestMapping("/competition")
public class CompetitionController {

    @Autowired
    private CompetitionRepository competitionRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private LeagueTableRepository leagueTableRepository;

    @RequestMapping("/test")
    public String process() {
        // Stworzenie zawodnikow
        List<Footballer> footballersPoland = new ArrayList<>();
        footballersPoland.add(new Footballer(23,"Test1"));
        footballersPoland.add(new Footballer(21,"Test2"));
        footballersPoland.add(new Footballer(22,"Test3"));
        List<Footballer> footballersGerman = new ArrayList<>();
        footballersGerman.add(new Footballer(24,"Test1GER"));
        footballersGerman.add(new Footballer(25,"Test2GER"));
        footballersGerman.add(new Footballer(26,"Test3GER"));

        Team team = new Team(1, "Polska");
        team.setFootballers(footballersPoland);
        Team team2 = new Team(2, "Germany");
        team2.setFootballers(footballersGerman);

        List<Team> teams = new ArrayList<>();
        teams.add(team);
        teams.add(team2);

        LeagueTable leagueTable = new LeagueTable("CAPTION");

        List<Standing> standings = new ArrayList<>();
        standings.add(new Standing(team,leagueTable));
        standings.add(new Standing(team2,leagueTable));
        leagueTable.setStandings(standings);

        MatchStatus matchStatus = new MatchStatus("FINISHED");

        Competition competition = new Competition(leagueTable, teams,22,"EURO");

        Result result = new Result(
                new PartialResult(0,1),
                new PartialResult(0,1),
                new PartialResult(0,1),
                new PartialResult(0,1)
                );

        Match match = new Match(competition,result,matchStatus,team,team2,22);

        matchRepository.save(match);
        competitionRepository.save(competition);

        return "Done";
    }

    @RequestMapping("/findAll")
    public String findAll() {
        String result = "<html>";
        for(Competition competiton: competitionRepository.findAll()) {
            result += "<div>" + competiton.toString() + "</div>";
        }
        return result + "</html>";
    }

    @RequestMapping("/delete")
    public String deleteAll() {
        leagueTableRepository.deleteAll();
        return "Deleted";
    }

}
