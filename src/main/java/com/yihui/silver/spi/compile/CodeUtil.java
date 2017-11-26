package com.yihui.silver.spi.compile;

import com.yihui.silver.spi.SpiLoader;
import com.yihui.silver.spi.api.SpiAdaptive;

import java.lang.reflect.Method;

/**
 * Created by yihui on 2017/5/25.
 */
public class CodeUtil {

    /**
     * 构建SPI接口的实现代理类, 在执行动态适配的方法时, 调用SpiLoader的 spiImpl选择器, 选择具体的实现类执行
     *
     * @return
     */
    public static String buildTempImpl(Class type) {
//        validate(type);


        StringBuilder codeBuilder = new StringBuilder();

        codeBuilder.append("package ").append(type.getPackage().getName()).append(";");
        codeBuilder.append("\nimport ").append(SpiLoader.class.getName()).append(";");
        codeBuilder.append("\npublic class ").append(type.getSimpleName()).append("$Proxy implements ").append(type.getCanonicalName()).append(" {\n");


        Method[] methods = type.getMethods();
        for (Method method : methods) {
            Class<?> returnType = method.getReturnType(); //函数返回值
            Class<?>[] parameterTypes = method.getParameterTypes();//函数参数列表
            Class<?>[] exceptionTypes = method.getExceptionTypes();//函数异常列表


            // build method code
            StringBuilder code = new StringBuilder(512);
            if (parameterTypes.length < 0) {   //检查该函数参数列表中，第一个参数作为选择器参数
                code.append("throw new IllegalArgumentException(\"there should be one argument for selector to choose spiImpl\")");
            } else { // 没有 SpiAdaptive注解的, 采用默认的注解方式
                code.append("try{\n");
                code.append(type.getName()).append(" spiImpl=")
                        .append("SpiLoader.load(")
                        .append(type.getName()).append(".class")
                        .append(").getService(arg0,\"")
                        .append(method.getName())
                        .append("\");");

                if (!"void".equals(returnType.getName())) {
                    code.append("return ");
                }
                code.append("spiImpl.").append(method.getName()).append("(arg0");
                for (int i = 1; i < parameterTypes.length; i++) {
                    code.append(",").append("arg").append(i);
                }
                code.append(");");
                code.append("\n} catch(com.yihui.silver.spi.exception.NoSpiMatchException e){\nthrow new java.lang.RuntimeException(e);\n}");
            }


            // build method signature
            codeBuilder.append("\npublic ").append(returnType.getName()).append(" ").append(method.getName())
                    .append("(").append(parameterTypes[0].getName()).append(" arg0");

            for (int i = 1; i < parameterTypes.length; i++) {
                codeBuilder.append(", ").append(parameterTypes[i].getName()).append(" arg").append(i);
            }
            codeBuilder.append(") ");
            if (exceptionTypes.length > 0) {
                codeBuilder.append("throw ").append(exceptionTypes[0].getName());
                for (int i = 1; i < exceptionTypes.length; i++) {
                    codeBuilder.append(", ").append(exceptionTypes[i].getName());
                }
            }
            codeBuilder.append("{\n");
            codeBuilder.append(code.toString()).append("\n}");
        }

        codeBuilder.append("\n}");
        return codeBuilder.toString();
    }


    /**
     * 要求接口方法 or 接口上必须有注解  {@link SpiAdaptive}
     *
     * @param type
     * @return true 则表示注解在接口上; 否则则方法上
     * @throws IllegalStateException
     */
    private static boolean validate(Class type) {
        Method[] methods = type.getMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(SpiAdaptive.class)) {
                return false;
            }
        }


        throw new IllegalStateException("No adaptive method on extension " + type.getName() + ", refuse to create the adaptive class!");
    }

}
