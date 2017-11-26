package com.yihui.silver.spi.test.echo;


import com.yihui.silver.spi.api.SpiConf;

/**
 * Created by yihui on 2017/5/24.
 */
@SpiConf(params = {"file"}, order = 3)
public class FileEcho implements IEcho {
    @Override
    public void echo(String str) {
        System.out.println("file echo! ... " + str);
    }
}
