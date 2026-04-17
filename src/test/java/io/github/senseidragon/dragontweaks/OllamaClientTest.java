package io.github.senseidragon.dragontweaks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OllamaClientTest {

    @Test
    void timeOfDay_morning_boundaries() {
        assertEquals("MORNING", OllamaClient.timeOfDay(0));
        assertEquals("MORNING", OllamaClient.timeOfDay(5999));
    }

    @Test
    void timeOfDay_afternoon_boundaries() {
        assertEquals("AFTERNOON", OllamaClient.timeOfDay(6000));
        assertEquals("AFTERNOON", OllamaClient.timeOfDay(11999));
    }

    @Test
    void timeOfDay_evening_boundaries() {
        assertEquals("EVENING", OllamaClient.timeOfDay(12000));
        assertEquals("EVENING", OllamaClient.timeOfDay(17999));
    }

    @Test
    void timeOfDay_night_boundaries() {
        assertEquals("NIGHT", OllamaClient.timeOfDay(18000));
        assertEquals("NIGHT", OllamaClient.timeOfDay(23999));
    }

    @Test
    void timeOfDay_wraps_at_24000() {
        assertEquals("MORNING", OllamaClient.timeOfDay(24000));
        assertEquals("MORNING", OllamaClient.timeOfDay(48000));
    }

    @Test
    void weather_clear() {
        assertEquals("CLEAR", OllamaClient.weather(false, false));
    }

    @Test
    void weather_raining() {
        assertEquals("RAINING", OllamaClient.weather(true, false));
    }

    @Test
    void weather_thunderstorm_takes_priority() {
        assertEquals("THUNDERSTORM", OllamaClient.weather(true, true));
        assertEquals("THUNDERSTORM", OllamaClient.weather(false, true));
    }

    @Test
    void parseResponse_extracts_response_field() {
        String json = "{\"model\":\"gemma4:26b\",\"response\":\"Hello there!\",\"done\":true}";
        assertEquals("Hello there!", OllamaClient.parseResponse(json));
    }

    @Test
    void parseResponse_handles_embedded_quotes() {
        String json = "{\"response\":\"He said \\\"hi\\\".\",\"done\":true}";
        assertEquals("He said \"hi\".", OllamaClient.parseResponse(json));
    }
}
