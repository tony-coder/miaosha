package com.bfx.miaosha.redis;

public class UserKey extends BasePrefix {

    public UserKey(String prefix) {
        super(prefix);
    }

    // public static UserKey getById = new UserKey("id");
    public static UserKey getById() {
        return new UserKey("id");
    }

    // public static UserKey getByName = new UserKey("name");
    public static UserKey getByName(){
        return new UserKey("name");
    }
}
