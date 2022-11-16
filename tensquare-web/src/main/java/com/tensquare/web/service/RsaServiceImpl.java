package com.tensquare.web.service;

import com.tensquare.web.rsa.RSA;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;

@Service
public class RsaServiceImpl implements RsaService {
    @Override
    public String RSADecryptDataPEM(String encryptData, String prvKey) throws Exception {
        byte[] encryptBytes = encryptData.getBytes();
        byte[] prvdata = RSA.decryptByPrivateKey(Base64Utils.decode(encryptData.getBytes()), prvKey);

        return new String(prvdata, StandardCharsets.UTF_8.name());
    }

    @Override
    public String RSADecryptDataBytes(byte[] encryptBytes, String prvKey) throws Exception {
        return null;
    }

    @Override
    public String RSAEncryptDataPEM(String data, String pubKey) throws Exception {
        return null;
    }

    @Override
    public String getRsaAlgorithm() {
        return null;
    }
}
