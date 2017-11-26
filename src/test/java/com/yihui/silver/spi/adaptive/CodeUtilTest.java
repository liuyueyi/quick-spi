package com.yihui.silver.spi.adaptive;

import com.yihui.silver.spi.SpiLoader;
import com.yihui.silver.spi.adaptive.code.ICode;
import com.yihui.silver.spi.compile.CodeUtil;
import com.yihui.silver.spi.exception.SpiProxyCompileException;
import com.yihui.silver.spi.selector.api.Context;
import groovy.lang.GroovyClassLoader;
import org.junit.Test;

/**
 * Created by yihui on 2017/5/25.
 */
public class CodeUtilTest {


    @Test
    public void testCodeUtil() {
        String code = CodeUtil.buildTempImpl(ICode.class);
        System.out.println(code);

//        try {
//            Class clz = JavassistCompiler.compile(code, this.getClass().getClassLoader());
//            Object obj = clz.newInstance();
//
//            Method method = clz.getMethod("print", String.class, String.class);
//            method.invoke(obj, "ConsoleCode", "test 自适应");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


    @Test
    public void testCodeUtil2() {
        String code = CodeUtil.buildTempImpl(ICode.class);

        ClassLoader parent = getClass().getClassLoader();
        GroovyClassLoader loader = new GroovyClassLoader(parent);
        Class clz = loader.parseClass(code);
        try {
            ICode bObject = (ICode) clz.newInstance();
            bObject.print("ConsoleCode", "test 自适应");
            System.out.println("--------");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testCodeSpi() throws SpiProxyCompileException {
        SpiLoader<ICode> loader = SpiLoader.load(ICode.class);
        ICode code = loader.getAdaptive();


        // 执行  ConsoleCode#print 方法
        code.print("ConsoleCode", "[ConsoleCode#echo adaptive console!]");


        // 执行 FileCode#pring 方法
        code.print("FileCode",  "[FileCode#echo adaptive console!]");



        // 执行 ConsoleCode#echo 方法
        code.echo("consoleEcho", "[ConsoleCode#echo 实现类上指定了name, 则可以直接指定]");
        code.echo("ConsoleCode", "[ConsoleCode#echo 实现类上指定了name, 也可以通过类级别的确定");


        // 执行 FileCode#echo
        code.echo("FileCode", "[FileCode#echo 实现类上没有指定name, 则默认采用类级别的确定]");



        Context context = new Context();
        context.setParam("type", "console");
        context.setParam("code");
        code.write(context, "[ConsoleCode#write 参数匹配输出]");


        context.clear();
        context.setParam("type", "file");
        context.setParam("code");
        code.write(context, "[FileCode#write 参数匹配输出");


        context.clear();
        context.setParam("code");
        code.pp(context, "[FileCode#pp 类条件匹配输出, file方法上优先级更高]");
    }


}
