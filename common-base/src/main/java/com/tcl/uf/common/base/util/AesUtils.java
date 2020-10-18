package com.tcl.uf.common.base.util;

import com.alibaba.fastjson.JSON;
import com.tcl.uf.common.base.dto.ActivityCodeInfo;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @ClassName: AesUtils
 * @Description:
 * @date 2018/3/20 下午4:52
 */

public class AesUtils {

    private static final String CHANNEL_AES_KEY = "JFBasiAj29hi9hmp21fg";

    /**
     * 加密
     * 1.构造密钥生成器
     * 2.根据ecnodeRules规则初始化密钥生成器
     * 3.产生密钥
     * 4.创建和初始化密码器
     * 5.内容加密
     * 6.返回字符串
     */
    public static String AESEncode(String encodeRules, String content) {
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(encodeRules.getBytes());
            keygen.init(128, secureRandom);
            //3.产生原始对称密钥
            SecretKey original_key = keygen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] raw = original_key.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key = new SecretKeySpec(raw, "AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byte_encode = content.getBytes("utf-8");
            //9.根据密码器的初始化方式--加密：将数据加密
            byte[] byte_AES = cipher.doFinal(byte_encode);
            //10.将加密后的数据转换为字符串
            //这里用Base64Encoder中会找不到包
            //解决办法：
            //在项目的Build path中先移除JRE System Library，再添加库JRE System Library，重新编译后就一切正常了。
            String AES_encode = new BASE64Encoder().encode(byte_AES);
            if(!StringUtils.isEmpty(AES_encode)) {
            	//替换URL中关键字符
            	AES_encode = AES_encode.replaceAll("/", "_").replaceAll("=", "-");
            	//据RFC 822规定，每76个字符，还需要加上一个回车换行
            	AES_encode = AES_encode.replaceAll("\r", "");
            	AES_encode = AES_encode.replaceAll("\n", "");
            }
            //11.将字符串返回
            return AES_encode;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //如果有错就返加nulll
        return null;
    }

    /**
     * 解密
     * 解密过程：
     * 1.同加密1-4步
     * 2.将加密后的字符串反纺成byte[]数组
     * 3.将加密内容解密
     */
    public static String AESDncode(String encodeRules, String content) {
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(encodeRules.getBytes());
            keygen.init(128, secureRandom);
            //3.产生原始对称密钥
            SecretKey original_key = keygen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] raw = original_key.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key = new SecretKeySpec(raw, "AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);
            //8.将加密并编码后的内容解码成字节数组
            content = content.replaceAll("_", "/").replaceAll("-", "=");
            byte[] byte_content = new BASE64Decoder().decodeBuffer(content);
            /*
             * 解密
             */
            byte[] byte_decode = cipher.doFinal(byte_content);
            String AES_decode = new String(byte_decode, "utf-8");
            return AES_decode;
        } catch (NoSuchAlgorithmException | InvalidKeyException | IOException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

        //如果有错就返加nulll
        return null;
    }

    public static String encodeChannel(ActivityCodeInfo code) {
        String s = JSON.toJSONString(code);
        return AESEncode(CHANNEL_AES_KEY, s);
    }

    public static ActivityCodeInfo decodeChannel(String content) {
        String s = AESDncode(CHANNEL_AES_KEY, content);
        return JSON.parseObject(s, ActivityCodeInfo.class);
    }
    
    public static void main(String[] args) {
		
//    	String paramGet = AesUtils.AESEncode("be959a41-dff5-4a71-a951-11bda1c1b355", "a=1@@b=2@@c=3");
    	String paramPost = AesUtils.AESEncode("be959a41-dff5-4a71-a951-11bda1c1b355", "{\"wxAppid\": \"wx257b842e53df71be\",\"thirdWxAppid\": \"wx1141db1932d73127\",\"activityCode\": \"10000\",\"cardType\":2,\"title\": \"测试第三方新建卡券\",\"getLimit\":10,\"quantity\":1000,\"beginTimeStamp\": 1533830400,\"endTimeStamp\": 1535558400}");
    	
    	try {
			System.out.println(URLEncoder.encode(paramPost, "utf-8"));
//			String res = HttpRequest.sendPost("http://localhost:7999/wxthird/thcard/create?resource=THDP&appid=201805224070", URLEncoder.encode(paramPost, "utf-8"));
//			System.out.println("res ===="+res);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//    	String params = AesUtils.AESDncode(paramAES, URLDecoder.decode(paramAES, "utf-8"));
    	
	}
    

}
