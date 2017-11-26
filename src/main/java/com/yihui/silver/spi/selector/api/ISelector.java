package com.yihui.silver.spi.selector.api;

import com.yihui.silver.spi.exception.NoSpiMatchException;

import java.util.Map;

/**
 * Created by yihui on 2017/5/24.
 */
public interface ISelector<T> {

    <K> K selector(Map<String, SpiImplWrapper<K>> map, T conf) throws NoSpiMatchException;
}
