package com.yihui.silver.spi.adaptive.code;


import com.yihui.silver.spi.api.Spi;
import com.yihui.silver.spi.api.SpiAdaptive;
import com.yihui.silver.spi.selector.ParamsSelector;
import com.yihui.silver.spi.selector.api.Context;

/**
 * Created by yihui on 2017/5/25.
 */
@Spi
public interface ICode {

    void print(String name, String contet);


    @SpiAdaptive
    void echo(String name, String content);


    @SpiAdaptive(selector = ParamsSelector.class)
    void write(Context context, String content);


    @SpiAdaptive(selector = ParamsSelector.class)
    void pp(Context context, String content);
}
