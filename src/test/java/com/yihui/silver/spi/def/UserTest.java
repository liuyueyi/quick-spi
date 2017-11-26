package com.yihui.silver.spi.def;

import com.yihui.silver.spi.SpiLoader;
import com.yihui.silver.spi.def.entity.MarketEnum;
import com.yihui.silver.spi.def.entity.UserDO;
import com.yihui.silver.spi.def.spi.IUser;
import com.yihui.silver.spi.exception.SpiProxyCompileException;
import org.junit.Test;

/**
 * Created by yihui on 2017/5/26.
 */
public class UserTest {


    @Test
    public void testUserSPI() throws SpiProxyCompileException {
        SpiLoader<IUser> loader = SpiLoader.load(IUser.class);
        IUser user = loader.getAdaptive();


        UserDO weixinUser = new UserDO();
        weixinUser.setAvatar("weixin.avatar.jpg");
        weixinUser.setUname("微信用户");
        weixinUser.setMarket(MarketEnum.WEIXIN);
        user.welcome(weixinUser);


        UserDO qqUser = new UserDO();
        qqUser.setAvatar("qq.avatar.jpg");
        qqUser.setUname("qq用户");
        qqUser.setMarket(MarketEnum.QQ);
        user.welcome(qqUser);

        System.out.println("-----over------");


    }

}
