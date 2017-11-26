package com.yihui.silver.spi.api;

import com.yihui.silver.spi.selector.DefaultSelector;
import com.yihui.silver.spi.selector.api.ISelector;

import java.lang.annotation.*;

/**
 * Created by yihui on 2017/5/22.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Spi {
    Class<? extends ISelector> selector() default DefaultSelector.class;
}
