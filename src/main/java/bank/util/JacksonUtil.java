package bank.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JacksonUtil {
    static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T deserialize(final String source, final Class<T> clazz) {
        try {
            return objectMapper.readValue(source, clazz);
        } catch (final JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T deserialize(final String source, final TypeReference<T> clazz) {
        try {
            return objectMapper.readValue(source, clazz);
        } catch (final JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String serialize(final Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();//
            mapper.registerModule(new JavaTimeModule());//
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writeValueAsString(object);
        } catch (final JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
