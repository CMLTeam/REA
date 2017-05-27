package com.cmlteam.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;

public class JsonUtil {
    private static final ObjectMapper OBJECT_MAPPER = prepareObjectMapper();

    private static ObjectMapper prepareObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        objectMapper.setDateFormat(df);
        return objectMapper;
    }

    public static String toJsonString(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<?> parseList(String json) {
        try {
            return OBJECT_MAPPER.readValue(json, List.class);
        } catch (IOException e) {
            throw new RuntimeException("Unable to parse json to list: " + Util.trim(json, 100), e);
        }
    }

    public static Map parseJson(String json) {
        try {
            return OBJECT_MAPPER.readValue(json, Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Unable to parse json to map: " + Util.trim(json, 100), e);
        }
    }

    public static <T> T parseJson(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Unable to parse json to class: " + Util.trim(json, 100), e);
        }
    }

    /**
     * @param jsonString JSON string representation (quoted)
     * @return Java string
     */
    public static String parseJsonString(String jsonString) {
        try {
            return OBJECT_MAPPER.readValue(jsonString, String.class);
        } catch (IOException e) {
            throw new RuntimeException("Unable to parseJsonString: " + Util.trim(jsonString, 100), e);
        }
    }

    public static String unescapeJson(String jsonStringContents) {
        Objects.requireNonNull(jsonStringContents);

        return parseJsonString('"' + jsonStringContents + '"');
    }

    public static String escapeJson(String string) {
        Objects.requireNonNull(string);

        String jsonString = toJsonString(string);
        return jsonString.substring(1, jsonString.length() - 1);// unquote
    }

    public static String prettyPrintJson(String json) {
        if (json == null)
            return null;
        try {
            Object parsed = OBJECT_MAPPER.readValue(json, Object.class);
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(parsed);
        } catch (IOException ignore) {
            return json;
        }
    }
}
