package io.github.senseidragon.dragontweaks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OllamaClientTest {

    @Test
    void timeOfDay_morning_boundaries() {
        assertEquals("MORNING", LLMClient.timeOfDay(0));
        assertEquals("MORNING", LLMClient.timeOfDay(5999));
    }

    @Test
    void timeOfDay_afternoon_boundaries() {
        assertEquals("AFTERNOON", LLMClient.timeOfDay(6000));
        assertEquals("AFTERNOON", LLMClient.timeOfDay(11999));
    }

    @Test
    void timeOfDay_evening_boundaries() {
        assertEquals("EVENING", LLMClient.timeOfDay(12000));
        assertEquals("EVENING", LLMClient.timeOfDay(17999));
    }

    @Test
    void timeOfDay_night_boundaries() {
        assertEquals("NIGHT", LLMClient.timeOfDay(18000));
        assertEquals("NIGHT", LLMClient.timeOfDay(23999));
    }

    @Test
    void timeOfDay_wraps_at_24000() {
        assertEquals("MORNING", LLMClient.timeOfDay(24000));
        assertEquals("MORNING", LLMClient.timeOfDay(48000));
    }

    @Test
    void weather_clear() {
        assertEquals("CLEAR", LLMClient.weather(false, false));
    }

    @Test
    void weather_raining() {
        assertEquals("RAINING", LLMClient.weather(true, false));
    }

    @Test
    void weather_thunderstorm_takes_priority() {
        assertEquals("THUNDERSTORM", LLMClient.weather(true, true));
        assertEquals("THUNDERSTORM", LLMClient.weather(false, true));
    }

    @Test
    void parseResponse_extracts_response_field() {
        String json = "{\"choices\":[{\"message\":{\"content\":\"Hello there!\"}}]}";
        assertEquals("Hello there!", LLMClient.parseResponse(json));
    }

    @Test
    void parseResponse_handles_embedded_quotes() {
        String json = "{\"choices\":[{\"message\":{\"content\":\"He said \\\"hi\\\".\"}}]}";
        assertEquals("He said \"hi\".", LLMClient.parseResponse(json));
    }
}
