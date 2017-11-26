package com.yihui.silver.spi.adaptive.code;


import com.yihui.silver.spi.api.SpiConf;
import com.yihui.silver.spi.selector.api.Context;

/**
 * Created by yihui on 2017/5/25.
 */
@SpiConf(params = "code")
public class FileCode implements ICode {
    @Override
    public void print(String name, String contet) {
        System.out.println("file print--->" + contet);
    }

    @Override
    public void echo(String name, String content) {
        System.out.println("file echo--->" + content);
    }


    @Override
    @SpiConf(params = {"type:file"})
    public void write(Context context, String content) {
        System.out.println("file write--->" + content);
    }

    @Override
    @SpiConf(order = 0)
    public void pp(Context context, String content) {
        System.out.println("file pp---->" + content);
    }
}
