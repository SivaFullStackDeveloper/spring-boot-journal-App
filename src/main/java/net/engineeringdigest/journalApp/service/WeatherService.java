package net.engineeringdigest.journalApp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import javax.annotation.PostConstruct;

@Component
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;




    @PostConstruct
    public  void getApiWeather() {

        String finalApiUrl = "https://jsonplaceholder.typicode.com/todos/1";
        var response = restTemplate.exchange(finalApiUrl, HttpMethod.GET,null,String.class);
        System.out.println(response.getBody());
    }
}
