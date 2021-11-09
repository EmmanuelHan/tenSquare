package com.tensquare.jwt;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import com.tensquare.common.util.StringUtil;

import java.util.Calendar;
import java.util.Date;

@Slf4j
public class CreateToken {

    public static void main(String[] args) {
        Calendar instance = Calendar.getInstance();
        Date time = instance.getTime();

        instance.add(Calendar.MINUTE,1);
        Date time1 = instance.getTime();


        JwtBuilder builder = Jwts.builder();
        builder.setId("888")
                .setSubject("小白")
                .setIssuedAt(time)
                .signWith(SignatureAlgorithm.HS256, StringUtil.TOKEN_KEY)
                .setExpiration(time1);
        log.info(builder.compact());


    }
}
