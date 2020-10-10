package common;

/**
 * @author: liushuai
 * @date: 2020/10/9
 * @description：验证码类
 */

import lombok.Data;

@Data
public class VerifyCode {
    private String code;

    private byte[] imgBytes;

    private long expireTime;
}
