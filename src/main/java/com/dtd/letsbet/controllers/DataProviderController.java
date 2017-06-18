package com.dtd.letsbet.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.dtd.letsbet.model.ExternalData.*;
import com.dtd.letsbet.repositories.ExternalRepositories.*;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Maciej on 04.06.2017.
 */

@RestController
@RequestMapping("/dataBaseProvider")
public class DataProviderController {
    @Autowired
    CompetitionRepository competitionRepository;
    @Autowired
    MatchRepository matchRepository;
    @Autowired
    LeagueTableRepository leagueTableRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    FootballerRepository footballerRepository;

    private Map<Integer, String> teamListUrl;
    private Map<Integer,String> leagueListUrl;
    private Map<Integer,String> fixtureListUrl;
    private Map<Integer, String> footballerListUrl;

    private MultiValueMap<Integer, Team> teamList;
    private MultiValueMap<Integer, Footballer> footballerList;
    private MultiValueMap<Integer, Competition> competitionList;
    private MultiValueMap<Integer, Match> matchList;
    private MultiValueMap<Integer, LeagueTable> leagueList;

    private static String competition = "http://api.football-data.org/v1/competitions/";

    @RequestMapping("/launch")
    public void saveToDatabase() {
        getData();
        deleteAll();
        saveAll();
    }

    enum sourceType{
        Competition(false, competition),
        Teams(true),
        Leagues(true),
        Fixtures(true),
        Footballers(true);
        private final boolean isList;

        private final String uri;

        sourceType(final boolean isList, final String uri){
            this.isList = isList;
            this.uri = uri;
        }

        sourceType(final boolean isList){
            this.isList = isList;
            this.uri = "";
        }

        public boolean isList(){
            return isList;
        }
        @Override
        public String toString(){
            return uri;
        }

    }


    public DataProviderController(){
        teamListUrl = new HashMap<>();
        leagueListUrl = new HashMap<>();
        fixtureListUrl = new HashMap<>();
        footballerListUrl = new HashMap<>();
        teamList = new LinkedMultiValueMap<>();
        competitionList = new LinkedMultiValueMap<>();
        matchList = new LinkedMultiValueMap<>();
        leagueList = new LinkedMultiValueMap<>();
        footballerList = new LinkedMultiValueMap<>();
    }

    private void getData(){
        for(sourceType source : sourceType.values()) {
            if (source.isList) {
                switch (source) {
                    case Teams:
                        readFromList(source, teamListUrl);
                        break;
                    case Leagues:
                        readFromList(source, leagueListUrl);
                        break;
                    case Fixtures:
                        readFromList(source, fixtureListUrl);
                        break;
                    case Footballers:
                        readFromList(source, footballerListUrl);
                        break;
                }
            } else {
                Object json = getJSONObject(source.toString());
                if (json != null) {
                    undressJSON(source, json, null);
                }
            }
        }
    }

    private void saveAll() {
        for(Integer competitionId : competitionList.keySet()) {
            for (Competition competition : competitionList.get(competitionId)) {
                List<Team> teams = teamList.get(competitionId);
                if(!teams.isEmpty()) {
                    List<Footballer> footballers = new ArrayList<>();
                    for (Team team : teams ) {
                        if(footballerList.containsKey(team.getName().hashCode()))
                            footballers = footballerList.get(team.getName().hashCode());
                        team.setFootballers(footballers);
                    }
                    teamRepository.save(teams);
                    competition.setTeams(teams);
                }
                List<Match> matches = matchList.get(competitionId);
                if(!matches.isEmpty()) {
                    matchRepository.save(matches);
                    competition.setMatches(matches);
                }
                competitionRepository.save(competition);
                leagueTableRepository.save(leagueList.get(competitionId));
            }
        }
    }

    private void deleteAll() {
        teamRepository.deleteAll();
        matchRepository.deleteAll();
        competitionRepository.deleteAll();
        leagueTableRepository.deleteAll();
    }

    private void translateCompetition(JSONObject jsonObject, int id) {
        Competition competition = new Competition();
        competition.setExternalId((int) getJsonObjectElement(jsonObject, "id"));
        competition.setCaption((String) getJsonObjectElement(jsonObject, "caption"));
        competition.setLeagueCode((String) getJsonObjectElement(jsonObject, "league"));
        competition.setYear(Integer.parseInt((String) getJsonObjectElement(jsonObject, "year")));
        competition.setNumberOfTeams((int) getJsonObjectElement(jsonObject, "numberOfTeams"));
        competition.setNumberOfGames((int) getJsonObjectElement(jsonObject, "numberOfGames"));
        competition.setLastUpdated(translateJsonDate((String) getJsonObjectElement(jsonObject, "lastUpdated")));
        competition.setCurrentMatchday((int) getJsonObjectElement(jsonObject, "currentMatchday"));
        competition.setNumberOfMatchdays((int) getJsonObjectElement(jsonObject, "numberOfMatchdays"));
        if(!competitionList.values().contains(competition))
            competitionList.add(id, competition);
    }

    private void translateTeams(JSONObject jsonObject, Integer id){
        Team team = new Team();
        team.setName((String) getJsonObjectElement(jsonObject, "name"));
        team.setCode((String) getJsonObjectElement(jsonObject, "code"));
        team.setShortName((String) getJsonObjectElement(jsonObject, "shortName"));
        team.setSquadMarketValue((String) getJsonObjectElement(jsonObject, "squadMarketValue"));
        team.setCrestUrl((String) getJsonObjectElement(jsonObject, "crestUrl"));
        team.setExternalId(team.getName().hashCode());
        if(!teamList.values().contains(team))
            teamList.add(id, team);
    }

    private void translateLeagues(JSONObject jsonObject, Integer id, List<Standing> standingList){
        LeagueTable league = new LeagueTable();
        league.setLeagueCaption((String) getJsonObjectElement(jsonObject, "leagueCaption"));
        league.setMatchDay((int) getJsonObjectElement(jsonObject, "matchday"));
        league.setStandings(standingList);
        for(Standing standing:standingList){
            standing.setLeagueTable(league);
        }
        if(!leagueList.values().contains(league))
            leagueList.add(id, league);
    }

    private Standing translateStandings(JSONObject jsonObject, Integer id) {
        String teamName =  (String) getJsonObjectElement(jsonObject, "team");
        Standing standing = new Standing();
        standing.setPlayedGames ((int) getJsonObjectElement(jsonObject, "playedGames"));
        standing.setTeamName (teamName);
        standing.setRank ((int) getJsonObjectElement(jsonObject, "rank"));
        standing.setCrestUrl ((String) getJsonObjectElement(jsonObject, "crestUrl"));
        standing.setGoalsAgainst ((int) getJsonObjectElement(jsonObject, "goalsAgainst"));
        standing.setGoalDifference ( (int) getJsonObjectElement(jsonObject, "goalDifference"));
        standing.setPoints ((int) getJsonObjectElement(jsonObject, "points"));
        standing.setGoals ((int) getJsonObjectElement(jsonObject, "goals"));
        if(teamList.containsKey(id)){
            List<Team> teamListTemp = teamList.get(id);
            for(Team team : teamListTemp)
                if (team.getName() == teamName) {
                    standing.setTeam(team);
                    break;
                }
        }
        return standing;
    }

    private void translateFixtures(JSONObject jsonObject, Integer id){
        Match match = new Match();
        match.setDate(translateJsonDate((String) getJsonObjectElement(jsonObject, "date")));
        match.setMatchday ((int) getJsonObjectElement(jsonObject, "matchday"));
        match.setAwayTeamName((String) getJsonObjectElement(jsonObject, "awayTeamName"));
        match.setHomeTeamName((String) getJsonObjectElement(jsonObject, "homeTeamName"));
        match.setMatchStatus(new MatchStatus((String) getJsonObjectElement(jsonObject, "status")));

        JSONObject oddsJSON = (JSONObject) getJsonObjectElement(jsonObject, "odds");
        if(oddsJSON != null){
            double away = (double) getJsonObjectElement(oddsJSON, "awayWin");
            double draw = (double) getJsonObjectElement(oddsJSON, "draw");
            double homeWin = (double) getJsonObjectElement(oddsJSON, "homeWin");
        }

        Result result = new Result();
        PartialResult finalResult = new PartialResult();
        JSONObject resultJSON = (JSONObject) getJsonObjectElement(jsonObject, "result");
        if(resultJSON != null){
            Object goalsHomeTeamObject = getJsonObjectElement(resultJSON, "goalsHomeTeam");
            if(goalsHomeTeamObject != null){
                finalResult.setGoalsHomeTeam((int) goalsHomeTeamObject);
            }

            Object goalsAwayTeamObject = getJsonObjectElement(resultJSON, "goalsAwayTeam");
            if(goalsAwayTeamObject != null){
                finalResult.setGoalsAwayTeam((int) goalsAwayTeamObject);
            }
        }
        result.setFinalResult(finalResult);
        match.setResult(result);
        if(!matchList.values().contains(match))
            matchList.add(id, match);
    }


    private void translateFootballers(JSONObject playerJSONObject, Integer id) {
        Footballer footballer = new Footballer();
        footballer.setName((String) getJsonObjectElement(playerJSONObject, "name"));
        footballer.setPosition((String) getJsonObjectElement(playerJSONObject, "position"));
        footballer.setJerseyNumber((int) getJsonObjectElement(playerJSONObject, "jerseyNumber"));
        footballer.setDateOfBirth(translateJsonDate((String) getJsonObjectElement(playerJSONObject, "dateOfBirth")));
        footballer.setNationality((String) getJsonObjectElement(playerJSONObject, "nationality"));
        footballer.setContractUntil(translateJsonDate((String) getJsonObjectElement(playerJSONObject, "contractUntil")));
        footballer.setMarketValue((String) getJsonObjectElement(playerJSONObject, "marketValue"));
        footballerList.add(id, footballer);
    }

    private void readFromList(sourceType source, Map<Integer, String> list) {
        for(Integer uri : list.keySet()){
            Object json = getJSONObject(list.get(uri));
            if(json != null){
                undressJSON(source, json, uri);
            }
        }
    }

    private void undressJSON(sourceType source, Object json, Integer id) {
        if(json instanceof JSONObject ){
            prepareToDatabase(source, (JSONObject)json, id);
        }
        else if(json instanceof JSONArray){
            for(Object probableJSONObject : ((JSONArray) json).toArray()){
                if(probableJSONObject instanceof JSONObject ){
                    prepareToDatabase(source, (JSONObject)probableJSONObject, id);
                }
                else if(probableJSONObject instanceof JSONArray){
                    undressJSON(source, probableJSONObject, id);
                }
            }
        }
    }

    private void prepareToDatabase(sourceType source, JSONObject jsonObject, Integer id){
        switch(source){
            case Competition:
                saveCompetition(jsonObject);
                break;
            case Teams:
                saveTeams(jsonObject, id);
                break;
            case Leagues:
                saveLeagues(jsonObject, id, saveStandings(jsonObject, id));
                break;
            case Fixtures:
                saveFixtures(jsonObject, id);
                break;
            case Footballers:
                saveFootballers(jsonObject, id);
                break;
        }
    }

    private void saveFootballers(JSONObject jsonObject, Integer id) {
        JSONArray players = (JSONArray) getJsonObjectElement(jsonObject, "players");
        for(Object playerJSONObject : ((JSONArray) players).toArray()){
            translateFootballers((JSONObject)playerJSONObject, id);
        }
    }

    private void saveCompetition(JSONObject jsonObject) {
        int externalId = (int) getJsonObjectElement(jsonObject, "id");
        fillListsFromCompetition(getJsonObjectElement(jsonObject, "_links"), externalId);
        translateCompetition(jsonObject, externalId);
    }

    private void saveTeams(JSONObject jsonObject, Integer id) {
        JSONArray teams = (JSONArray) getJsonObjectElement(jsonObject, "teams");
        for(Object teamJSONObject : ((JSONArray) teams).toArray()){
            fillListsFromTeams(getJsonObjectElement((JSONObject)teamJSONObject, "_links"), (String)getJsonObjectElement((JSONObject)teamJSONObject, "name"));
            translateTeams((JSONObject)teamJSONObject, id);
        }
    }

    private void fillListsFromTeams(Object jsonLinks, String name) {
        fillGenericList(footballerListUrl, "players", jsonLinks, name.hashCode());
    }

    private void fillListsFromCompetition(Object jsonLinks, Integer id) {
        fillGenericList(teamListUrl, "teams", jsonLinks, id);
        fillGenericList(leagueListUrl, "leagueTable", jsonLinks, id);
        fillGenericList(fixtureListUrl, "fixtures", jsonLinks, id);
    }

    private void saveLeagues(JSONObject jsonObject, Integer id, List<Standing> standingList) {
        translateLeagues(jsonObject, id, standingList);
    }

    private List<Standing> saveStandings(JSONObject jsonObject, Integer id) {
        return getStandings(jsonObject, id);
    }

    private void saveFixtures(JSONObject jsonObject, Integer id) {
        JSONArray fixtures = (JSONArray) getJsonObjectElement(jsonObject, "fixtures");
        for(Object fixturesJSONObject : ((JSONArray) fixtures).toArray()){
            translateFixtures((JSONObject)fixturesJSONObject, id);
        }
    }

    private void fillGenericList(Map<Integer, String> listOfObjects, String name, Object jsonLinks, Integer id){
        Object object = getJsonObjectElement(((JSONObject)jsonLinks), name);
        String uri = (String) getJsonObjectElement(((JSONObject)object), "href");
        if(!listOfObjects.containsKey(id)){
            listOfObjects.put(id, uri);
        }
    }

    private void fillGenericList(Map<String, String> listOfObjects, String name, Object jsonLinks, String id){
        Object object = getJsonObjectElement(((JSONObject)jsonLinks), name);
        String uri = (String) getJsonObjectElement(((JSONObject)object), "href");
        if(!listOfObjects.containsKey(id)){
            listOfObjects.put(id, uri);
        }
    }


    private List<Standing> getStandings(JSONObject jsonObject, Integer id) {
        JSONObject standingsGroup = (JSONObject) getJsonObjectElement(jsonObject, "standings");
        List<Standing> standingList = new ArrayList<>();
        if(standingsGroup != null){
            for(String key : standingsGroup.keySet()){
                JSONArray standings = (JSONArray)standingsGroup.get(key);
                for(Object standing : standings){
                    JSONObject standingJSON = (JSONObject)standing;
                    standingList.add(translateStandings(standingJSON, id));
                }
            }
        }
        return standingList;
    }

    private Object getJSONObject(String uri){
        String json = readJsonFromSite(uri);

        if(json!=null){
            JSONParser parser = new JSONParser();
            try {
                Object obj = parser.parse(json);
                return obj;
            }
            catch(net.minidev.json.parser.ParseException e){
                return null;
            }
        }
        return null;
    }

    private String readJsonFromSite(String uri){
        HttpURLConnection m_connection = null;
        try{
            URL url = new URL(uri);
            m_connection = (HttpURLConnection) url.openConnection();
            m_connection.setRequestMethod("GET");
            m_connection.addRequestProperty("X-Auth-Token","e136b7858d424b9da07c88f28b61989a");
            m_connection.connect();

            InputStream inputStream = m_connection.getInputStream();
            Scanner scan = new Scanner(inputStream);
            String json = "";
            while (scan.hasNext())
                json += scan.nextLine();
            scan.close();
            return json;
        }
        catch(UnsupportedEncodingException e){
            return null;
        }
        catch(IOException e){
            return null;
        }
    }

    private Date translateJsonDate(String jsonDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(jsonDate);
        } catch (ParseException e) {
            return null;
        }
    }

    private Object getJsonObjectElement(JSONObject obj, String name){
        return obj.get(name);
    }
}
