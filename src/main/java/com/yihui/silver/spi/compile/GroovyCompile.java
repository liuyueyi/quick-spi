package com.yihui.silver.spi.compile;

import com.yihui.silver.spi.exception.SpiProxyCompileException;
import groovy.lang.GroovyClassLoader;

/**
 * Created by yihui on 2017/5/25.
 */
public class GroovyCompile {


    @SuppressWarnings("unchecked")
    public static <T> T compile(String code, Class<T> interfaceType, ClassLoader classLoader) throws SpiProxyCompileException {
        GroovyClassLoader loader = new GroovyClassLoader(classLoader);
        Class clz = loader.parseClass(code);

        if (!interfaceType.isAssignableFrom(clz)) {
            throw new IllegalStateException("illegal proxy type!");
        }


        try {
            return (T) clz.newInstance();
        } catch (Exception e) {
            throw new SpiProxyCompileException("init spiProxy error! msg: " + e.getMessage());
        }
    }

}
