package work.gg3083.template.cache.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import work.gg3083.template.cache.ICache;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@Component
@Slf4j
public class MemoryCacheImpl implements ICache {


    private final ConcurrentHashMap<String, List<Object>> cache = new ConcurrentHashMap<>();

    public MemoryCacheImpl() {

    }


    @Override
    public void put(String key, List<Object> value) {
        if (StringUtils.isEmpty(key)){
            return;
        }
        if (StringUtils.isEmpty(value)){
            cache.remove(value);
            return;
        }
        List<Object> cacheValList = cache.get(key);
        if (CollectionUtils.isEmpty(cacheValList)) {
            cacheValList = value;
        }else {
            cacheValList.addAll(value);
        }
        cache.put(key, cacheValList);
    }



    @Override
    public ConcurrentHashMap<String, List<Object>> list() {

        return cache;
    }

    @Override
    public void remove(String key) {
        cache.remove(key);
    }

    @Override
    public Object get(String key) {
        List<Object> value = cache.get(key);
        return value;
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public long size() {
        return cache.size();
    }

}