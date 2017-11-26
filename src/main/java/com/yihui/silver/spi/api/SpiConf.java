package com.yihui.silver.spi.api;

import java.lang.annotation.*;

/**
 *
 * Created by yihui on 2017/5/24.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface SpiConf {

    /**
     * 唯一标识
     *
     * @return
     */
    String name() default "";


    /**
     * 参数过滤, 单独一个元素,表示参数必须包含; 用英文分号,左边为参数名,右边为参数值,表示参数的值必须是右边的
     * <p/>
     * 形如  {"a", "a:12", "b:TAG"}
     *
     * @return
     */
    String[] params() default {};


    /**
     * 排序, 越小优先级越高
     *
     * @return
     */
    int order() default -1;
}
