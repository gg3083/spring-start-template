package work.gg3083.template.cache;


import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public interface ICache<T> {

    void put(String key, List<T> value);

    void remove(String key);

    List<T> get(String key);

    void clear();

    long size();

    ConcurrentHashMap<String, List<T>> list();
}