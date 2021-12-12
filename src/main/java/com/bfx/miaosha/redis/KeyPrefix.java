package com.bfx.miaosha.redis;

public interface KeyPrefix {
    int expireSeconds();

    String getPrefix();
}
