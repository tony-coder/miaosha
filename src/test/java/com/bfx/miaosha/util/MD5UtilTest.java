package com.bfx.miaosha.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class MD5UtilTest {

    @Test
    public void md5() {
        System.out.println(MD5Util.md5("123456"));
    }

    @Test
    public void inputPassToFormPass() {
        System.out.println(MD5Util.inputPassToFormPass("123456"));
    }

    @Test
    public void formPassToDbPass() {
        System.out.println(MD5Util.formPassToDbPass("123456", "1a2b3c4d"));
    }

    @Test
    public void inputPassToDbPass() {
        System.out.println(MD5Util.inputPassToDbPass("123456", "1a2b3c4d"));
    }
}