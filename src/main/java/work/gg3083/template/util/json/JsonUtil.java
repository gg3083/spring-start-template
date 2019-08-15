package work.gg3083.template.util.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Gimi
 * @date 2019/6/8 12:22
 */
public class JsonUtil {
    public static ObjectMapper mapper = new ObjectMapper();

    /**
     * 将jsonp转换成json
     * @param jsonp
     * @return
     */
    public static String jsonpToJson(String jsonp) {
        if (StringUtils.isEmpty(jsonp)) {
            return null;
        }
        int index = jsonp.indexOf("(");
        int lastIndex = jsonp.indexOf(")");
        String json = null;
        if (index != -1 && lastIndex != -1) {
            json = jsonp.substring(index + 1, lastIndex);
        }
        return json;
    }


    /**
     * json转换成java对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <T> T jsonToBean(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        T t = mapper.readValue(json, clazz);
        return t;
    }


    /**
     * 将java对象转换成json
     * @param t
     * @param <T>
     * @return
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <T> String beanToJson(T t) throws JsonGenerationException, JsonMappingException, IOException {

        String json = mapper.writeValueAsString(t);
        return json;
    }


    /**
     * 将json转换成list
     * @param jsonStr
     * @param collectionClass
     * @param elementClass
     * @param <T>
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <T> List<T> jsonToList(String jsonStr, Class<?> collectionClass, Class<T> elementClass) throws JsonParseException, JsonMappingException, IOException {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClass, elementClass);
        List<T> list = mapper.readValue(jsonStr, javaType);
        return list;
    }


    public static <T> Map<String, Object> jsonToMap(String jsonStr) throws Exception {
        return mapper.readValue(jsonStr, Map.class);
    }
}
