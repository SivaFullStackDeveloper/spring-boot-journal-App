package net.engineeringdigest.journalApp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    @Value("{weather.api.key}")
    private static  String apiKey;
    private static final String apiUrl = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;



    public  void getApiWeather(String city) {
        String finalApiUrl = apiUrl.replace("API_KEY",apiKey).replace("CITY",city);
        var response = restTemplate.exchange(finalApiUrl, HttpMethod.GET,null,String.class);
        System.out.println(response.getBody());
    }
}
