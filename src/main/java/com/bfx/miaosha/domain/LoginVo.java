package com.bfx.miaosha.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginVo {
    private String mobile;
    private String password;
}
