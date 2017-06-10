package com.dtd.letsbet.externDataProvider;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;;

/**
 * Created by Maciej on 04.06.2017.
 */
public class DataProvider {
    private List<String> teamList;
    private List<String> leagueList;

    private static String competition = "http://api.football-data.org/v1/competitions/";


    public DataProvider(){
        teamList = new  ArrayList();
        leagueList = new  ArrayList();
    }

    enum sourceType{
        Competition(false, competition),
        Teams(true),
        Leagues(true);

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
                }
            }
            else{
                Object json = getJSONObject(source.toString());
                undressJSON(source, json);
            }
        }
    }

    private void readFromList(sourceType source, List<String> list) {
        for(String uri : list){
            Object json = getJSONObject(uri);
            undressJSON(source, json);
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
                saveLeaguesAndStandings(jsonObject);
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

    private void saveLeaguesAndStandings(JSONObject jsonObject) {
        translateLeagues(jsonObject);
        translateStandings(jsonObject);
    }

    private void fillListsFromCompetition(Object jsonLinks) {
        Object teamObject = getJsonObjectElement(((JSONObject)jsonLinks), "teams");
        String uri = (String) getJsonObjectElement(((JSONObject)teamObject), "href");
        if(!teamList.contains(uri)){
            teamList.add(uri);
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
        String matchday = (String) getJsonObjectElement(jsonObject, "matchday");
    }

    private void translateStandings(JSONObject jsonObject) {
        // TODO Auto-generated method stub

    }


    private Object getJSONObject(String uri){
        String json = readJsonFromSite(uri);

        @SuppressWarnings("deprecation")
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(json);
            return obj;
        }
        catch(net.minidev.json.parser.ParseException e){
            return null;
        }
    }

    private String readJsonFromSite(String uri){
        try{
            URL url = new URL(uri);

            Scanner scan = new Scanner(url.openStream());
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
