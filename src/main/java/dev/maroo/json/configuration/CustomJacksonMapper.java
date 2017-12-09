package dev.maroo.json.configuration;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import dev.maroo.json.conventer.CustomLocalDateDeserializer;
import dev.maroo.json.conventer.CustomLocalDateSerializer;
import dev.maroo.json.conventer.CustomLocalDateTimeDeserializer;
import dev.maroo.json.conventer.CustomLocalDateTimeSerializer;
import dev.maroo.json.conventer.CustomLocalTimeDeserializer;
import dev.maroo.json.conventer.CustomLocalTimeSerializer;

/**
 *
 * @author Maroo
 */
public class CustomJacksonMapper extends ObjectMapper {

    private static final long serialVersionUID = 6676639462863745282L;

    public CustomJacksonMapper() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer());
        module.addSerializer(LocalDate.class, new CustomLocalDateSerializer());
        module.addSerializer(LocalTime.class, new CustomLocalTimeSerializer());
        module.addDeserializer(LocalDateTime.class, new CustomLocalDateTimeDeserializer());
        module.addDeserializer(LocalDate.class, new CustomLocalDateDeserializer());
        module.addDeserializer(LocalTime.class, new CustomLocalTimeDeserializer());
        this.registerModule(module);
        this.setSerializationInclusion(Include.NON_NULL);
    }
}
