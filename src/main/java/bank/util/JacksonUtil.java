package bank.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
            return objectMapper.writeValueAsString(object);
        } catch (final JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
