package work.gg3083.template.cache;


import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public interface ICache {

    void put(String key, List<Object> value);

    void remove(String key);

    Object get(String key);

    void clear();

    long size();

    ConcurrentHashMap<String, List<Object>> list();
}