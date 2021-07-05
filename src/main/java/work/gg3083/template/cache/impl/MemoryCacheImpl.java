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
public class MemoryCacheImpl<T> implements ICache<T> {


    private final ConcurrentHashMap<String, List<T>> cache = new ConcurrentHashMap<>();

    public MemoryCacheImpl() {

    }


    @Override
    public void put(String key, List<T> value) {
        if (StringUtils.isEmpty(key)){
            return;
        }
        if (StringUtils.isEmpty(value)){
            cache.remove(value);
            return;
        }
        List<T> cacheValList = cache.get(key);
        if (CollectionUtils.isEmpty(cacheValList)) {
            cacheValList = value;
        }else {
            cacheValList.addAll(value);
        }
        cache.put(key, cacheValList);
    }



    @Override
    public ConcurrentHashMap<String, List<T>> list() {

        return cache;
    }

    @Override
    public void remove(String key) {
        cache.remove(key);
    }

    @Override
    public List<T> get(String key) {
        return cache.get(key);
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