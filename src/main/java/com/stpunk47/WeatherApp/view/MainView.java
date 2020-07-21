package com.stpunk47.WeatherApp.view;


import com.stpunk47.WeatherApp.controller.WeatherService;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
public class MainView extends UI {

    @Autowired
    private WeatherService weatherService;


    @Override
    protected void init(VaadinRequest request) {

        try {
            JSONArray jsonArray = weatherService.weatherDataArray("lviv");
            JSONObject mainData = weatherService.getWeatherMainData("lviv");
            int visibilityData = weatherService.getVisibilityData("lviv");
            JSONObject windData = weatherService.getWindData("lviv");
            JSONObject cloudsData = weatherService.getCloudsData("lviv");
            JSONObject sysData = weatherService.getSysData("lviv");

            System.out.println("Temperature: "+mainData.getString("temp"));
            System.out.println("Feels like: "+mainData.getString("feels_like"));
            System.out.println("Temp MIN: "+mainData.getString("temp_min"));
            System.out.println("Temp MAX: "+mainData.getString("temp_max"));
            System.out.println("Pressure: "+mainData.getString("pressure"));
            System.out.println("Humidity: "+mainData.getString("humidity"));

            System.out.println("Visibility: "+visibilityData);

            System.out.println("Wind speed: "+windData.getDouble("speed"));
            System.out.println("Wind degrees: "+windData.getDouble("deg"));

            System.out.println("Clouds: "+cloudsData.getString("all"));

            System.out.println("System data TYPE: "+sysData.getString("type"));
            System.out.println("System data ID: "+sysData.getString("id"));
            System.out.println("System data Country: "+sysData.getString("country"));
            System.out.println("System data SUNRISE: "+sysData.getLong("sunrise"));
            System.out.println("System data SUNSET: "+sysData.getLong("sunset"));



            for(int i=0; i<jsonArray.length(); i++){
                JSONObject weatherObject = jsonArray.getJSONObject(i);
                System.out.println("Id: "+weatherObject.getInt("id")
                +", main: "+weatherObject.getString("main")
                +", description: "+weatherObject.getString("description"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
