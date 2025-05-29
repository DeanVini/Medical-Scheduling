package com.api.medical_scheduling.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.time.*;

@Configuration
public class JacksonConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();

        module.addDeserializer(ZonedDateTime.class, new CustomZonedDateTimeDeserializer());

        objectMapper.registerModule(module);
        return objectMapper;
    }

    public static class CustomZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {
        @Override
        public ZonedDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            String dateTimeString = p.getText();
            try {
                return ZonedDateTime.parse(dateTimeString);
            } catch (java.time.format.DateTimeParseException e) {
                try {
                    LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString);
                    return localDateTime.atZone(ZoneId.systemDefault());
                } catch (Exception ex) {
                    throw new IOException("Erro ao converter data: " + dateTimeString, ex);
                }
            }
        }
    }
}