package com.devops.menu.util;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.JavaType;

import java.io.IOException;
import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/11/15
 * @description：
 */
public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);
    }


    /**
     * Object对象转json
     *
     * @param pojo
     * @return
     * @throws JsonMappingException
     * @throws JsonGenerationException
     * @throws IOException
     */
    public static String toJson(Object pojo) throws JsonMappingException, JsonGenerationException, IOException {
        return objectMapper.writeValueAsString(pojo);
    }


    /**
     * json字符串转 List对象
     *
     * @param str   json字符串
     * @param clazz 转换的类型
     * @return
     */
    public static <T> List<T> fromListJson(String str, Class<?> clazz, Class<?>... parameterClasses) throws Exception {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(clazz, parameterClasses);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
