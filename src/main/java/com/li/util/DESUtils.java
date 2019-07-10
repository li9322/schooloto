package com.li.util;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import com.thoughtworks.xstream.core.util.Base64Encoder;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @ClassName: DESUtils
 * @Description: DES是一种对称加密算法。 所谓对称加密算法就是指使用相同的密钥
 * @author: libl
 * @date: 2019/07/08 11:04
 */
@SuppressWarnings("restriction")
public class DESUtils {
    private static Key key;
    // 设置密钥key
    private static String KEY_STR = "myKey";
    private static String CHARSETNAME = "UTF-8";
    private static String ALGORITHM = "DES";

    static {
        try {
            // 生成DES算法对象
            KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
            // 运行SHA1安全策略
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            // 设置上密钥种子
            secureRandom.setSeed(KEY_STR.getBytes());
            // 初始化基于SHA1的算法对象
            generator.init(secureRandom);
            // 生成密钥对象
            key = generator.generateKey();
            generator = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 获取加密后的信息
     * @Param: str
     * @return: String
     */
    public static String getEncryptString(String str) {
        // 基于BASE64编码，接收byte[]并转换为String
        Base64Encoder base64Encoder = new Base64Encoder();
        try {
            // 按UTF-8编码
            byte[] bytes = str.getBytes(CHARSETNAME);
            // 获取加密对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 初始化密码信息
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 加密
            byte[] doFinal = cipher.doFinal(bytes);
            // byte[] to encode好的String并返回
            return base64Encoder.encode(doFinal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 获取解密之后的信息
     * @Param: str
     * @return: String
     */
    public static String getDecryptString(String str) {
        // 基于BASE64编码，接收byte[]并转换为String
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            // 将字符串decode成byte[]
            byte[] bytes = base64Decoder.decodeBuffer(str);
            // 获取解密对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 初始化解密信息
            cipher.init(Cipher.DECRYPT_MODE, key);
            // 解密
            byte[] doFinal = cipher.doFinal(bytes);
            // 返回解密之后的信息
            return new String(doFinal, CHARSETNAME);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 测试
    public static void main(String[] args) {
        System.out.println(getEncryptString("root"));
        System.out.println(getEncryptString("liBAO@12"));
        System.out.println(getDecryptString("WnplV/ietfQ="));
        System.out.println(getDecryptString("23v//68BVpYfJAfVsP+M2w=="));
    }
}
