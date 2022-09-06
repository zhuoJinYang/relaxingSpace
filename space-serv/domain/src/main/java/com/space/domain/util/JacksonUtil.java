package com.space.domain.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

/**
 * Jackson工具类
 */
public class JacksonUtil {
    public static final ObjectMapper mapper = new ObjectMapper();
    /**
     * DataTime格式化字符串
     */
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * Data格式化字符串
     */
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * Time格式化字符串
     */
    private static final String TIME_FORMAT = "HH:mm:ss";
    static {
        //序列化的时候序列对象的那些属性
        //* JsonInclude.Include.NON_DEFAULT 属性为默认值不序列化
        //* JsonInclude.Include.ALWAYS      所有属性
        //* JsonInclude.Include.NON_EMPTY   属性为 空（“”） 或者为 NULL 都不序列化
        //* JsonInclude.Include.NON_NULL    属性为NULL 不序列化
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 修改序列化后日期格式
        mapper.setDateFormat(new SimpleDateFormat(DATETIME_FORMAT));
        // 设置时间类模块
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        // 添加序列化和反序列化
        // LocalDate
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_FORMAT)));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_FORMAT)));
        // LocalTime
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(TIME_FORMAT)));
        // LocalDateTime
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT)));
        mapper.registerModule(javaTimeModule);

        // 自动扫描并注册相关模块
        mapper.findAndRegisterModules();
        //反序列化时,遇到未知属性会不会报错
        //true - 遇到没有的属性就报错 false - 没有的属性不会管，不会报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 转换为格式化的json 显示出来的格式美化
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    private JacksonUtil() {}

    // TODO 下面的看不懂,之后再去理解
    public static ObjectNode createObjectNode() {
        return mapper.createObjectNode();
    }

    public static ArrayNode createArrayNode() {
        return mapper.createArrayNode();
    }

    public static String toString(Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof String) {
            return (String) object;
        }
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JsonNode toJsonNode(String str) {
        if (!StringUtils.hasText(str)) {
            return null;
        }
        try {
            return mapper.readTree(str);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JsonNode toJsonNode(Object obj) {
        if (obj == null) {
            return null;
        }
        return mapper.convertValue(obj, JsonNode.class);
    }

    public static <T> T toObject(Object obj, Class<T> clazz) {
        if (obj == null) {
            return null;
        }
        try {
            return mapper.readValue(toString(obj), clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> toList(Object obj, Class<T> clazz) {
        return toList(toString(obj), clazz);
    }

    public static <T> List<T> toList(String str, Class<T> clazz) {
        if (!StringUtils.hasText(str)) {
            return Collections.emptyList();
        }
        try {
            return mapper.readValue(str, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static boolean isNull(JsonNode jsonNode) {
        return jsonNode == null || jsonNode.isNull();
    }

    public static JsonNode getJsonNode(JsonNode jsonNode, String key) {
        return isNull(jsonNode) ? null : jsonNode.get(key);
    }

    public static String getTextValue(JsonNode jsonNode, String key) {
        if (isNull(jsonNode)) {
            return null;
        }
        JsonNode value = jsonNode.get(key);
        return isNull(value) && !value.isTextual() ? null : value.asText();
    }

    public static Integer getIntValue(JsonNode jsonNode, String key) {
        if (isNull(jsonNode)) {
            return null;
        }
        JsonNode value = jsonNode.get(key);
        return isNull(value) && !value.isInt() ? null : value.asInt();
    }
}
