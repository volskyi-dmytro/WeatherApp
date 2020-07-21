package com.stpunk47.WeatherApp.view;


import com.stpunk47.WeatherApp.controller.WeatherService;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
public class MainView extends UI {

    @Autowired
    private WeatherService weatherService;


    @Override
    protected void init(VaadinRequest request) {

        try {
            System.out.println("Data: "+
                    weatherService.getWeather("Lviv").getString("coord").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
