package com.yihui.silver.spi.selector;

import com.yihui.silver.spi.exception.NoSpiMatchException;
import com.yihui.silver.spi.selector.api.Context;
import com.yihui.silver.spi.selector.api.ISelector;
import com.yihui.silver.spi.selector.api.SpiImplWrapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by yihui on 2017/5/24.
 */
public class ParamsSelector implements ISelector<Context> {

    private ConcurrentHashMap<Integer, List<SpiImplWrapper>> cacheListMap = new ConcurrentHashMap<>();


    @Override
    @SuppressWarnings("unchecked")
    public <K> K selector(Map<String, SpiImplWrapper<K>> map, Context conf) throws NoSpiMatchException {
        if (map == null || map.size() == 0) {
            throw new IllegalArgumentException("no impl spi!");
        }


        for (SpiImplWrapper<K> wrapper : getList(map)) {
            if (match(wrapper, conf)) {
                return wrapper.getSpiImpl();
            }
        }

        throw new NoSpiMatchException("no spiImpl satisfy conf! spiImplList: " + map.keySet() + " conf: " + conf);
    }


    private boolean match(SpiImplWrapper wrapper, Context conf) {
        Map<String, String> condition = wrapper.getParamCondition();


        Map<String, String> confParams = conf.getParams();


        for (Map.Entry<String, String> entry : condition.entrySet()) {
            if (!confParams.containsKey(entry.getKey())) {
                return false;
            }

            if (!Objects.equals(confParams.get(entry.getKey()), entry.getValue())) {
                return false;
            }
        }
        return true;
    }


    /**
     * 需要针对优先级进行排序, 并将列表缓存起来
     *
     * @param map
     * @param <K>
     * @return
     */
    private <K> List<SpiImplWrapper> getList(Map<String, SpiImplWrapper<K>> map) {
        int code = map.hashCode();
        if (cacheListMap.containsKey(code)) {
            return cacheListMap.get(code);
        }

        List<SpiImplWrapper> list = map.values().stream().sorted().collect(Collectors.toList());
        cacheListMap.putIfAbsent(code, list);
        return list;
    }
}
