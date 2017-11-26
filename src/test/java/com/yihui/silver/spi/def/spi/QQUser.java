package com.yihui.silver.spi.def.spi;

import com.yihui.silver.spi.def.entity.UserDO;

/**
 * Created by yihui on 2017/5/26.
 */
public class QQUser implements IUser {

    @Override
    public void welcome(UserDO userDO) {
        System.out.println("qq 欢迎你! " + userDO);
    }
}
