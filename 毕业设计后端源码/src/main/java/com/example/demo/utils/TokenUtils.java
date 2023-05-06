package com.example.demo.utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

/**
 * @author MECHREVO
 */
public class TokenUtils {

    public static String genToken(String userId, String sign){
        /**
         * 将userId 保存到token中,作为载荷
         * 2小时后token过期
         * 以password作为token的秘钥
         */
        return JWT.create().withAudience(userId)
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2))
                .sign(Algorithm.HMAC256(sign));
    }
}
