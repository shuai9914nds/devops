package common;

/**
 * 错误信息枚举类
 *
 * @author zzb
 * @version 1.1.0 2017/06/20
 */
public enum ErrorCode {
    /**
     * 公共错误码
     **/
    MY_ERROR(9000, ""),//自定义错误
    CONFIG_ERROR(9001, "配置缺失"),
    DB_ERROR(9002, "数据库访问报错"),
    DATA_NOT_EXIST_ERROR(9003, "数据不存在"),
    DATA_CONVERTER_ERROR(9004, "数据转换失败"),
    ACTION_OPERATE_INFO_ERROR(9005, "操作信息缺失"),
    DUPLICATE(9006, "重复提交"),
    STORE_SERVER_ERROR(9007, "库存server异常"),
    DATA_INCONSIS(9008, "数据不一致"),
    OPERATION_FORBIDDEN(9009, "禁止操作"),

    PARAM_ERROR(100001, "参数非法"),
    PARAM_OUT_RANGE_ERROR(100002, "参数的值超出范围"),

    TOKEN_IS_NULL(200001, "token不能为空"),


    /**
     * userinfo服务
     */
    UNAME_OR_PASSWORD_ERROR(300001, "用户名或者密码错误"),
    VERIFY_CODE_ERROR(300002, "验证码错误"),

    /**
     * role服务
     */
    ROLE_NAME_REPEAT(400001, "角色名称重复"),


    /**
     * 具体业务错误码
     **/


    SYSTEM_ERROR(999999, "接口异常");
    private Integer code;
    private String message;

    private ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
