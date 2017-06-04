package com.dtd.letsbet.externDataProvider;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 * Created by Maciej on 04.06.2017.
 */
public class DataProvider {
    public DataProvider(){}

    enum sourceType{
        //write model with path from which we take data
        Competition("competitions/");

        private final String text;

        private sourceType(final String text){
            this.text = text;
        }

        @Override
        public String toString(){
            return text;
        }
    }

    private void getData(){
        //partially so you can see how it looks like, rest of it and transaltions too model is a long work - i will push it as soon as ready

        for(sourceType source : sourceType.values()) {
            convertToModel(source);
        }
    }

    private void convertToModel(sourceType source){
        JSONObject json = getJSONObject(source.toString());
        switch(source){
            case Competition:
                //to do - convert to model
                break;
        }
    }


    private JSONObject getJSONObject(String additionalUri){
        String uri = "http://api.football-data.org/v1/" +  additionalUri;
        String json = readJsonFromSite(uri);

        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(json);
            return obj;
        }
        catch(ParseException e){
            return null;
        }
    }

    private String readJsonFromSite(String uri){
        try{
            String encodedUri = URLEncoder.encode(uri, "UTF-8");
            URL url = new URL(encodedUri);

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

}
