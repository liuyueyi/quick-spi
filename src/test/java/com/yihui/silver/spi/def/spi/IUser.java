package com.yihui.silver.spi.def.spi;

import com.yihui.silver.spi.api.Spi;
import com.yihui.silver.spi.api.SpiAdaptive;
import com.yihui.silver.spi.def.entity.UserDO;

/**
 * Created by yihui on 2017/5/26.
 */
@Spi
public interface IUser {


    @SpiAdaptive(selector = UserSelector.class)
    void welcome(UserDO userDO);


}
