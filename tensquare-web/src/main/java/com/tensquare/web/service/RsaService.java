package com.tensquare.web.service;

public interface RsaService {

    /***
     * RSA解密
     *
     * @param encryptData
     * @return
     * @throws Exception
     */
    String RSADecryptDataPEM(String encryptData, String prvKey) throws Exception;

    /***
     * RSA解密
     *
     * @param encryptBytes
     * @return
     * @throws Exception
     */
    String RSADecryptDataBytes(byte[] encryptBytes, String prvKey) throws Exception;

    /***
     * RSA加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    String RSAEncryptDataPEM(String data, String pubKey) throws Exception;

    String getRsaAlgorithm();
}
