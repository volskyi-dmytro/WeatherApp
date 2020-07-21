package com.stpunk47.WeatherApp.view;


import com.stpunk47.WeatherApp.controller.WeatherService;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ClassResource;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@SpringUI
public class MainView extends UI {

    @Autowired
    private WeatherService weatherService;
    private VerticalLayout mainLayout;
    private NativeSelect<String> unitSelect;
    private TextField cityTextField;
    private Button showWeatherButton;
    private Label currentLocationTitle;
    private Label currentTemp;
    private Label weatherDescription;
    private Label weatherMin;
    private Label weatherMax;
    private Label pressureLabel;
    private Label humidityLabel;
    private Label windSpeedLabel;
    private Label sunriseLabel;
    private Label sunsetLabel;
    private ExternalResource img;
    private Embedded image;


    @Override
    protected void init(VaadinRequest request) {
        setLayout();
        setHeader();
        setLogo();
        setUpForm();
        dashBoardTitle();
        dashBoardDescription();

        showWeatherButton.addClickListener(clickEvent ->{
            if (!cityTextField.getValue().equals("")) {
                try {
                    updateUI();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else Notification.show("Please enter a city ");
        });

    }

    private void setLayout(){
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

        Label title = new Label("Погода для тупеньких");
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
        formLayout.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        formLayout.setSpacing(true);
        formLayout.setMargin(true);

        // adding selection of units
        unitSelect = new NativeSelect<>();
        unitSelect.setWidth("40px");
        ArrayList<String> items = new ArrayList<>();
        items.add("C");
        items.add("F");

        unitSelect.setItems(items);
        unitSelect.setValue(items.get(0));
        formLayout.addComponent(unitSelect);


        //adding cityTextField for input
        cityTextField = new TextField();
        cityTextField.setWidth("80%");
        formLayout.addComponent(cityTextField);


        //adding 'show weather' button
        showWeatherButton = new Button();
        showWeatherButton.setIcon(VaadinIcons.SEARCH);
        formLayout.addComponent(showWeatherButton);


        mainLayout.addComponent(formLayout);


    }

    private void dashBoardTitle() {
        HorizontalLayout dashBoardMain = new HorizontalLayout();
        dashBoardMain.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);


        image = new Embedded(null, img);



        currentLocationTitle = new Label("Currently in ...");
        currentLocationTitle.addStyleName(ValoTheme.LABEL_H2);
        currentLocationTitle.addStyleName(ValoTheme.LABEL_LIGHT);

        currentTemp = new Label("20C");
        currentTemp.addStyleName(ValoTheme.LABEL_H1);
        currentTemp.addStyleName(ValoTheme.LABEL_BOLD);
        currentTemp.addStyleName(ValoTheme.LABEL_LIGHT);

        dashBoardMain.addComponents(currentLocationTitle, image, currentTemp);

        mainLayout.addComponent(dashBoardMain);




    }

    private void dashBoardDescription() {
        HorizontalLayout mainDescriptionLayout = new HorizontalLayout();
        mainDescriptionLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        //Description Vertical Layout
        VerticalLayout descriptionVerticalLayout = new VerticalLayout();
        descriptionVerticalLayout.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        descriptionVerticalLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        weatherDescription = new Label("Description: Clear Skies");
        descriptionVerticalLayout.addComponent(weatherDescription);

        weatherMin = new Label("Weather Min: 12C");
        descriptionVerticalLayout.addComponent(weatherMin);

        weatherMax = new Label("Weather Max: 22C");
        descriptionVerticalLayout.addComponent(weatherMax);


        //Pressure, humidity etc...
        VerticalLayout pressureLayout = new VerticalLayout();
        pressureLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        pressureLabel = new Label("Pressure is: 400pa");
        pressureLayout.addComponent(pressureLabel);

        humidityLabel = new Label("Humidity is: 47");
        pressureLayout.addComponent(humidityLabel);

        windSpeedLabel = new Label("Wind speed: 40/hr");
        pressureLayout.addComponent(windSpeedLabel);

        sunriseLabel = new Label("Sunrise: 123123123");
        pressureLayout.addComponent(sunriseLabel);

        sunsetLabel = new Label("Sunset: 123123443");
        pressureLayout.addComponent(sunsetLabel);

        mainDescriptionLayout.addComponents(descriptionVerticalLayout, pressureLayout);
        mainLayout.addComponent(mainDescriptionLayout);

    }

    private void updateUI() throws JSONException {
        String city = cityTextField.getValue().toUpperCase();

        currentLocationTitle.setValue("Currently in "+city);
        JSONObject mainData = weatherService.getWeatherMainData(city);

        int temp = mainData.getInt("temp");
        currentTemp.setValue(temp+"C");

        String iconCode = null;
        JSONArray jsonArray = weatherService.weatherDataArray(city);
        for(int i = 0; i<jsonArray.length();i++){
            JSONObject weatherObject = jsonArray.getJSONObject(i);
            iconCode = weatherObject.getString("icon");
        }


        img = new ExternalResource("http://openweathermap.org/img/wn/"+iconCode+"@2x.png");


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
