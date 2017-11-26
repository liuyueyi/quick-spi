package com.yihui.silver.spi.def.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by yihui on 2017/5/26.
 */
@Getter
@Setter
@ToString
public class UserDO {

    private String uname;

    private String avatar;

    private MarketEnum market;

}
