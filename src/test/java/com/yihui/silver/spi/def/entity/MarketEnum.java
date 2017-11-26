package com.yihui.silver.spi.def.entity;

/**
 * Created by yihui on 2017/5/26.
 */
public enum MarketEnum {
    WEIXIN("WeixinUser"),

    QQ("QQUser");

    private String name;

    MarketEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
