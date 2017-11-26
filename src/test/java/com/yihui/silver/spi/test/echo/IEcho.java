package com.yihui.silver.spi.test.echo;


import com.yihui.silver.spi.api.Spi;
import com.yihui.silver.spi.selector.ParamsSelector;

/**
 * Created by yihui on 2017/5/24.
 */
@Spi(selector = ParamsSelector.class)
public interface IEcho {
    void echo(String str);
}
