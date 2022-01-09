package com.bfx.miaosha.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

@Slf4j
public class MD5Util {
    private static final String salt = "1a2b3c4d";  // 固定salt

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    /**
     * 用户端：PASS=MD5(明文+固定Salt)
     *
     * @param inputPass 输入密码明文
     * @return
     */
    public static String inputPassToFormPass(String inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        log.info(str);
        return md5(str);
    }

    /**
     * 服务端：PASS=MD5(用户输入+随机Salt)
     *
     * @param formPass
     * @param salt
     * @return
     */
    public static String formPassToDbPass(String formPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        log.info(str);
        return md5(str);
    }

    public static String inputPassToDbPass(String inputPass, String saltDB) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDbPass(formPass, saltDB);
        log.info(dbPass);
        return dbPass;
    }


}
