package com.bfx.miaosha.util;

import java.util.UUID;

public class UUIDUtil {
    public static String uuid() {
        // 生成随机uuid（去掉原生的横杠）
        return UUID.randomUUID().toString().replace("-", "");
    }
}
