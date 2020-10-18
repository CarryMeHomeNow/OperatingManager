package com.tcl.uf.common.base.util;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA加密工具类
 *
 * @author huangrc
 */
public class RSAUtil {

    private static final Logger logger = LoggerFactory.getLogger(RSAUtil.class);

    /**
     * 公钥对象KEY
     */
    public static final String PUBLIC_KEY = "publicKey";

    /**
     * 私钥对象KEY
     */
    public static final String PRIVATE_KEY = "privateKey";

    /**
     * 生成公私钥对
     *
     * @param keySize 公私钥对大小
     * @return 公私钥对象和Base64编码后的公私钥String字符串
     * @throws NoSuchAlgorithmException
     * @since 10.15
     */
    public static Map<String, Key> createKey(int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = null;
        keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(keySize, new SecureRandom());
        KeyPair key = keyGen.generateKeyPair();
        PublicKey pubKey = key.getPublic();
        PrivateKey priKey = key.getPrivate();
        Map<String, Key> map = new HashMap<String, Key>();
        map.put(PUBLIC_KEY, pubKey);
        map.put(PRIVATE_KEY, priKey);
        return map;
    }

    /**
     * 公钥或私钥转为字符串
     */
    public static String encode(Key key) {
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * 通过字符串转为私钥
     */
    public static PrivateKey decodePrivateKey(String privateKeyData) {
        PrivateKey privateKey = null;
        try {
            byte[] decodeKey = Base64.decodeBase64(privateKeyData); //将字符串Base64解码
            PKCS8EncodedKeySpec pkcs8 = new PKCS8EncodedKeySpec(decodeKey);//创建pkcs8证书封装类
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");//指定RSA
            privateKey = keyFactory.generatePrivate(pkcs8);//生成私钥
        } catch (Exception e) {
            logger.error("通过字符串转为私钥异常,privateKeyData=" + privateKeyData, e);
        }
        return privateKey;
    }


    /**
     * 通过字符串转为公钥
     */
    public static PublicKey decodePublicKey(String publicKeyData) {
        PublicKey publicKey = null;
        try {
            byte[] decodeKey = Base64.decodeBase64(publicKeyData);
            X509EncodedKeySpec x509 = new X509EncodedKeySpec(decodeKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(x509);
        } catch (Exception e) {
            logger.error("通过字符串转为公钥异常,publicKeyData=" + publicKeyData, e);
        }
        return publicKey;
    }

    /**
     * RSA加密<br>
     * 注意：此方法支持公钥加密私钥解密或者私钥加密公钥解密
     *
     * @param key     公钥或者私钥
     * @param message 准备加密的原文
     * @return 加密后的密文
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String encrypt(Key key, String message) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] miwen = cipher.doFinal(message.getBytes());
        return Base64.encodeBase64String(miwen);
    }

    /**
     * RSA解密<br>
     * 注意：此方法支持公钥加密私钥解密或者私钥加密公钥解密
     *
     * @param key     私钥或者公钥
     * @param message 已加密的密文
     * @return 解密后的原文
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String decrypt(Key key, String message) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] miwen = Base64.decodeBase64(message);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] result = cipher.doFinal(miwen);
        return new String(result);
    }

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        Map<String, Key> map = RSAUtil.createKey(2048);
        long createKey = System.currentTimeMillis();
        System.out.println("生成密码时间：" + (createKey - start));

        System.out.println("PUBLIC_KEY："+RSAUtil.encode(map.get(RSAUtil.PUBLIC_KEY)));
        System.out.println("PRIVATE_KEY："+RSAUtil.encode(map.get(RSAUtil.PRIVATE_KEY)));

        String en = RSAUtil.encrypt(RSAUtil.decodePublicKey(RSAUtil.encode(map.get(RSAUtil.PUBLIC_KEY))), "11111111");
        long encrypt = System.currentTimeMillis();
        System.out.println("加密时间：" + (encrypt - createKey));
        System.out.println(en);

        String de = RSAUtil.decrypt(RSAUtil.decodePrivateKey(RSAUtil.encode(map.get(RSAUtil.PRIVATE_KEY))), en);
        long decrypt = System.currentTimeMillis();
        System.out.println("解密时间：" + (decrypt - encrypt));
        System.out.println(de);
    }
}
