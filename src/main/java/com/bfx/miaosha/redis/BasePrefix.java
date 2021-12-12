package com.bfx.miaosha.redis;

public abstract class BasePrefix implements KeyPrefix {
    private int expireSeconds;

    private String prefix;

    // 默认0代表永不过期
    public BasePrefix(String prefix) {
        this.prefix = prefix;
        this.expireSeconds = 0;
    }

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ";" + prefix;
    }
}
