package com.yihui.silver.spi.selector.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yihui on 2017/5/24.
 */
@Getter
@Setter
@ToString
public class Context {

    private Map<String, String> params = new HashMap<>();


    public String getParam(String key) {
        return params.get(key);
    }


    public void setParam(String key, String value) {
        params.put(key, value);
    }

    public void setParam(String key) {
        setParam(key, null);
    }


    public void clear() {
        params.clear();
    }
}