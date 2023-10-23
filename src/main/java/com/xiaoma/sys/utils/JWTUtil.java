package com.xiaoma.sys.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 生成Token
 *
 * @author mjh
 */
public class JWTUtil {
    /**
     * token秘钥
     */
    private static final String SECRET = "JWT+SHIRO";

    /**
     * 用户
     */
    private static final String USERNAME = "username";



    /**
     * 生成token
     * @param username 用户名
     * @param expiredDate 过期时间
     */
    public static String createToken(String username, Date expiredDate) throws UnsupportedEncodingException {


        Map<String, Object> mapHeader = new HashMap<>();
        mapHeader.put("alg", "HS256");
        mapHeader.put("typ", "JWT");

        // 生成token
        return JWT.create()
                .withHeader(mapHeader)
                .withClaim(USERNAME, username)  // 私有声明
                .withExpiresAt(expiredDate) // 过期时间
                .withIssuedAt(new Date())  // 签发时间
                .sign(Algorithm.HMAC256(SECRET)); // 签名
    }

    /**
     * 校验token的有效性
     */
    public static Boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            verifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    /**
     * 获取Token中的信息
     */
    public static String getClaim(String token , String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(claim).asString();
        }catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取用户username
     */
    public static String getUsername() {
        return USERNAME;
    }
}
