package com.yihui.silver.spi.test.echo;


import com.yihui.silver.spi.api.SpiConf;

/**
 * Created by yihui on 2017/5/24.
 */
@SpiConf(params = {"console"})
public class ConsoleEcho implements IEcho{

    @Override
    public void echo(String str) {
        System.out.println("ConsoleEcho: ..." + str);
    }
}
