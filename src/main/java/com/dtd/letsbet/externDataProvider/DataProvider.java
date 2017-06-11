package com.dtd.letsbet.externDataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

/**
 * Created by Maciej on 04.06.2017.
 */
public class DataProvider {
    private List<String> teamList;
    private List<String> leagueList;
    private List<String> fixtureList;

    private static String competition = "http://api.football-data.org/v1/competitions/";

    enum sourceType{
        Competition(false, competition),
        Teams(true),
        Leagues(true),
        Fixtures(true);

        private final boolean isList;
        private final String uri;

        private sourceType(final boolean isList, final String uri){
            this.isList = isList;
            this.uri = uri;
        }

        private sourceType(final boolean isList){
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

    public DataProvider(){
        teamList = new  ArrayList();
        leagueList = new  ArrayList();
        fixtureList = new  ArrayList();
    }

    public void getData(){
        for(sourceType source : sourceType.values()) {
            if(source.isList){
                switch(source){
                    case Teams:
                        readFromList(source, teamList);
                        break;
                    case Leagues:
                        readFromList(source, leagueList);
                        break;
                    case Fixtures:
                        readFromList(source, fixtureList);
                        break;
                }
            }
            else{
                Object json = getJSONObject(source.toString());
                if(json != null){
                    undressJSON(source, json);
                }
            }
        }
    }

    private void translateCompetition(JSONObject jsonObject) {
        int externalId = (int) getJsonObjectElement(jsonObject, "id");
        String caption= (String) getJsonObjectElement(jsonObject, "caption");
        String leagueCode= (String) getJsonObjectElement(jsonObject, "league");
        String year = (String) getJsonObjectElement(jsonObject, "year");
        int numberOfTeams= (int) getJsonObjectElement(jsonObject, "numberOfTeams");
        int numberOfGames= (int) getJsonObjectElement(jsonObject, "numberOfGames");
        Date lastUpdated= translateJsonDate((String) getJsonObjectElement(jsonObject, "lastUpdated"));
        int currentMatchday= (int) getJsonObjectElement(jsonObject, "currentMatchday");
        int numberOfMatchdays= (int) getJsonObjectElement(jsonObject, "numberOfMatchdays");
    }

    private void translateTeams(JSONObject jsonObject){
        String name = (String) getJsonObjectElement(jsonObject, "name");
        String code = (String) getJsonObjectElement(jsonObject, "code");
        String shortName = (String) getJsonObjectElement(jsonObject, "shortName");
        String squadMarketValue = (String) getJsonObjectElement(jsonObject, "squadMarketValue");
        String crestUrl = (String) getJsonObjectElement(jsonObject, "crestUrl");
    }

    private void translateLeagues(JSONObject jsonObject){
        String leagueCaption = (String) getJsonObjectElement(jsonObject, "leagueCaption");
        int matchday = (int) getJsonObjectElement(jsonObject, "matchday");
    }

    private void translateStandings(JSONObject jsonObject) {
        int teamId = (int) getJsonObjectElement(jsonObject, "teamId");
        int playedGames = (int) getJsonObjectElement(jsonObject, "playedGames");
        String team = (String) getJsonObjectElement(jsonObject, "team");
        int rank = (int) getJsonObjectElement(jsonObject, "rank");
        String crestUrl = (String) getJsonObjectElement(jsonObject, "crestUrl");
        int goalsAgainst = (int) getJsonObjectElement(jsonObject, "goalsAgainst");
        int goalDifference = (int) getJsonObjectElement(jsonObject, "goalDifference");
        String group = (String) getJsonObjectElement(jsonObject, "group");
        int points = (int) getJsonObjectElement(jsonObject, "points");
        int goals = (int) getJsonObjectElement(jsonObject, "goals");
    }

    private void translateFixtures(JSONObject jsonObject) {
        Date date = translateJsonDate((String) getJsonObjectElement(jsonObject, "date"));
        int matchday = (int) getJsonObjectElement(jsonObject, "matchday");
        String awayTeamName = (String) getJsonObjectElement(jsonObject, "awayTeamName");
        String homeTeamName = (String) getJsonObjectElement(jsonObject, "homeTeamName");
        String status = (String) getJsonObjectElement(jsonObject, "status");

        JSONObject oddsJSON = (JSONObject) getJsonObjectElement(jsonObject, "odds");
        if(oddsJSON != null){
            double awayWin = (double) getJsonObjectElement(oddsJSON, "awayWin");
            double draw = (double) getJsonObjectElement(oddsJSON, "draw");
            double homeWin = (double) getJsonObjectElement(oddsJSON, "homeWin");
        }

        JSONObject resultJSON = (JSONObject) getJsonObjectElement(jsonObject, "result");
        if(resultJSON != null){
            Object goalsHomeTeamObject = getJsonObjectElement(resultJSON, "goalsHomeTeam");
            if(goalsHomeTeamObject != null){
                int goalsHomeTeam = (int) goalsHomeTeamObject;
            }

            Object goalsAwayTeamObject = getJsonObjectElement(resultJSON, "goalsAwayTeam");
            if(goalsAwayTeamObject != null){
                int goalsAwayTeam = (int) goalsHomeTeamObject;
            }
        }
    }


    private void readFromList(sourceType source, List<String> list) {
        for(String uri : list){
            Object json = getJSONObject(uri);
            if(json != null){
                undressJSON(source, json);
            }
        }
    }

    private void undressJSON(sourceType source, Object json) {
        if(json instanceof JSONObject ){
            saveToDatabase(source, (JSONObject)json);
        }
        else if(json instanceof JSONArray){
            for(Object probableJSONObject : ((JSONArray) json).toArray()){
                if(probableJSONObject instanceof JSONObject ){
                    saveToDatabase(source, (JSONObject)probableJSONObject);
                }
                else if(probableJSONObject instanceof JSONArray){
                    undressJSON(source, probableJSONObject);
                }
            }
        }
    }

    private void saveToDatabase(sourceType source, JSONObject jsonObject){
        switch(source){
            case Competition:
                saveCompetition(jsonObject);
                break;
            case Teams:
                saveTeams(jsonObject);
                break;
            case Leagues:
                saveLeagues(jsonObject);
                saveStandings(jsonObject);
                break;
            case Fixtures:
                saveFixtures(jsonObject);
                break;
        }
    }

    private void saveCompetition(JSONObject jsonObject) {
        fillListsFromCompetition(getJsonObjectElement(jsonObject, "_links"));
        translateCompetition(jsonObject);
    }

    private void saveTeams(JSONObject jsonObject) {
        JSONArray teams = (JSONArray) getJsonObjectElement(jsonObject, "teams");
        for(Object teamJSONObject : ((JSONArray) teams).toArray()){
            translateTeams(jsonObject);
        }
    }

    private void saveLeagues(JSONObject jsonObject) {
        translateLeagues(jsonObject);
    }

    private void saveStandings(JSONObject jsonObject) {
        getStandings(jsonObject);
    }

    private void saveFixtures(JSONObject jsonObject) {
        JSONArray fixtures = (JSONArray) getJsonObjectElement(jsonObject, "fixtures");
        for(Object fixturesJSONObject : ((JSONArray) fixtures).toArray()){
            translateFixtures((JSONObject)fixturesJSONObject);
        }
    }

    private void fillListsFromCompetition(Object jsonLinks) {
        fillGenericList(teamList, "teams", jsonLinks);
        fillGenericList(leagueList, "leagueTable", jsonLinks);
        fillGenericList(fixtureList, "fixtures", jsonLinks);
    }

    private void fillGenericList(List<String> listOfObjects, String name, Object jsonLinks){
        Object object = getJsonObjectElement(((JSONObject)jsonLinks), name);
        String uri = (String) getJsonObjectElement(((JSONObject)object), "href");
        if(!listOfObjects.contains(uri)){
            listOfObjects.add(uri);
        }
    }


    private void getStandings(JSONObject jsonObject) {
        JSONObject standingsGroup = (JSONObject) getJsonObjectElement(jsonObject, "standings");
        if(standingsGroup != null){
            for(String key : standingsGroup.keySet()){
                JSONArray standings = (JSONArray)standingsGroup.get(key);
                for(Object standing : standings){
                    JSONObject standingJSON = (JSONObject)standing;
                    translateStandings(standingJSON);
                }
            }
        }
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
