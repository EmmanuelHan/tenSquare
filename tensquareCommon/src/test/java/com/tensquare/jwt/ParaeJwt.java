package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import util.StringUtil;

@Slf4j
public class ParaeJwt {

    public static void main(String[] args) {
        String taken = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE1ODQ2Njg3NTMsImV4cCI6MTU4NDY2ODgxM30.BzprIx_y-x8wyH-4BTQTy4UZBt8B2NcN6FB5623TxRc";
        Claims claims =
                Jwts.parser().setSigningKey(StringUtil.TOKEN_KEY).parseClaimsJws(taken).getBody();
        log.info("id:"+claims.getId());
        log.info("subject:"+claims.getSubject());
        log.info("IssuedAt:"+claims.getIssuedAt());


    }
}
