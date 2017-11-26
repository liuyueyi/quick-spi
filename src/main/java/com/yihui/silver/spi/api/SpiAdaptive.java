package com.yihui.silver.spi.api;

import com.yihui.silver.spi.selector.DefaultSelector;
import com.yihui.silver.spi.selector.api.ISelector;

import java.lang.annotation.*;

/**
 * SPI 自适应注解, 表示该类or该方法会用到spi实现
 * <p/>
 * Created by yihui on 2017/5/24.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface SpiAdaptive {
    Class<? extends ISelector> selector() default DefaultSelector.class;
}
