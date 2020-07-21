package com.stpunk47.WeatherApp.view;


import com.stpunk47.WeatherApp.controller.WeatherService;
import com.vaadin.server.ClassResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@SpringUI
public class MainView extends UI {

    @Autowired
    private WeatherService weatherService;
    private VerticalLayout mainLayout;
    private NativeSelect<String> unitSelect;


    @Override
    protected void init(VaadinRequest request) {
        setLayout();
        setHeader();
        setLogo();
        setUpForm();

    }




    public void setLayout(){
        mainLayout = new VerticalLayout();
        mainLayout.setWidth("100%");
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);

        mainLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        setContent(mainLayout);
    }

    private void setHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        Label title = new Label("Weather");
        title.addStyleName(ValoTheme.LABEL_H1);
        title.addStyleName(ValoTheme.LABEL_BOLD);
        title.addStyleName(ValoTheme.LABEL_COLORED);

        header.addComponent(title);



        mainLayout.addComponent(header);

    }

    private void setLogo() {
        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        Image weatherIcon =
                new Image(null, new ClassResource("/weather/weather_icons-02.png"));
        weatherIcon.setWidth("125px");
        weatherIcon.setHeight("125px");

        logoLayout.addComponent(weatherIcon);
        mainLayout.addComponent(logoLayout);
    }

    private void setUpForm() {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        formLayout.setSpacing(true);
        formLayout.setMargin(true);

        unitSelect = new NativeSelect<>();
        unitSelect.setWidth("40px");
        ArrayList<String> items = new ArrayList<>();
        items.add("C");
        items.add("F");

        unitSelect.setItems(items);
        unitSelect.setValue(items.get(0));
        formLayout.addComponent(unitSelect);
        mainLayout.addComponent(formLayout);


    }
}

/*

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


 */
