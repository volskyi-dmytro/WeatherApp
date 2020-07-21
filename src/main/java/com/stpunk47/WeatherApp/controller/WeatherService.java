package com.stpunk47.WeatherApp.controller;



import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

}
