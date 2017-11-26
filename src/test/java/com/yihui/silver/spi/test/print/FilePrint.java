package com.yihui.silver.spi.test.print;

/**
 * Created by yihui on 2017/5/24.
 */
public class FilePrint implements IPrint {
    @Override
    public void print(String str) {
        System.out.println("file print: " + str);
    }

    @Override
    public void adaptivePrint(String conf, String str) {
        System.out.println("file adaptivePrint: " + str);
    }
}
