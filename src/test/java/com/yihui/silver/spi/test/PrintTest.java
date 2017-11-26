package com.yihui.silver.spi.test;

import com.yihui.silver.spi.SpiLoader;
import com.yihui.silver.spi.exception.NoSpiMatchException;
import com.yihui.silver.spi.exception.SpiProxyCompileException;
import com.yihui.silver.spi.test.print.IPrint;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yihui on 2017/5/24.
 */
public class PrintTest {

    @Test
    public void testPrint() throws NoSpiMatchException {
        SpiLoader<IPrint> spiLoader = SpiLoader.load(IPrint.class);

        IPrint print = spiLoader.getService("ConsolePrint");
        print.print("console---->");


        print = spiLoader.getService("FilePrint");
        print.print("file---->");


        try {
            print = spiLoader.getService("undefine");
            print.print("undefine----");
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("type error-->" + e);
        }


        try {
            print = spiLoader.getService(123);
            print.print("type error----");
            Assert.assertTrue(false);
        } catch (Exception e){
            System.out.println("type error-->" + e);
        }
    }


    @Test
    public void testAdaptivePrint() throws SpiProxyCompileException {
        IPrint print = SpiLoader.load(IPrint.class).getAdaptive();


        print.adaptivePrint("FilePrint", "[file print]");
        print.adaptivePrint("ConsolePrint", "[console print]");
    }
}
