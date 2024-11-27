package net.engineeringdigest.journalApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // Method to set data to Redis
    public void setToRedis(String key, Object response, Long ttl) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonValue = mapper.writeValueAsString(response);
        redisTemplate.opsForValue().set(key, jsonValue, ttl, TimeUnit.MILLISECONDS);  // Set TTL in milliseconds
    }

    // Method to get data from Redis and deserialize it into the desired class
    public <T> T getToRedis(String key, Class<T> responseType) throws JsonProcessingException {
        String jsonValue = redisTemplate.opsForValue().get(key);

        if (jsonValue != null) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonValue, responseType);  // Deserialize the value to the expected type
        }

        return null;  // Return null if key is not found in Redis
    }
}
