package com.stpunk47.WeatherApp.controller;



import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WeatherService {

    private OkHttpClient client;
    private Response response;

    public JSONObject getWeather(String name) throws JSONException {
        client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://api.openweathermap.org/data/2.5/weather?q="+name+"&units=metric&appid=b6d24de27eb46df9ba1abefe3f68fdba")
                .build();
        try {
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray weatherDataArray(String name) throws JSONException{

            JSONArray weatherJSONArray = getWeather(name).getJSONArray("weather");

            return weatherJSONArray;

    }

    public JSONObject getWeatherMainData(String name) throws JSONException {
        JSONObject weatherMainData = getWeather(name).getJSONObject("main");

        return weatherMainData;
    }

    public int getVisibilityData(String name) throws JSONException {
        int visibilityData = getWeather(name).getInt("visibility");

        return visibilityData;
    }

    public JSONObject getWindData(String name) throws JSONException {
        JSONObject windData = getWeather(name).getJSONObject("wind");

        return windData;
    }

    public JSONObject getCloudsData(String name) throws JSONException {
        JSONObject cloudsData = getWeather(name).getJSONObject("clouds");

        return cloudsData;
    }

    public JSONObject getSysData(String name) throws JSONException {
        JSONObject sysData = getWeather(name).getJSONObject("sys");

        return sysData;
    }


}
