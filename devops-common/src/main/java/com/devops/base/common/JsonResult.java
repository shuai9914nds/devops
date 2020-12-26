package com.devops.base.common;

import com.devops.base.utils.JacksonUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * dubbo接口公共返回类型
 *
 * @author ls
 * @version 1.1.0 2020/05/15
 */
@Data
public class JsonResult implements Serializable {
    private static final long serialVersionUID = 144081677442025340L;
    private Boolean success = true;
    private Integer errorCode = 0;//防止空指针异常，0表示没有异常
    private String errorMessage = "";

    /**
     * Result对象默认构造函数
     */
    public JsonResult() {
    }

    /**
     * Result对象构造函数
     *
     * @param success 详见{@link Boolean}
     */
    public JsonResult(Boolean success) {
        super();
        this.success = success;
    }

    /**
     * Result对象默认构造函数
     *
     * @param errorCode 详见{@link ErrorCode}
     */
    public JsonResult(ErrorCode errorCode) {
        super();
        this.success = false;
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage();
    }

    /**
     * Result对象默认构造函数
     *
     * @param errorCode 详见{@link ErrorCode}
     * @param msg       详见{@link String}
     */
    public JsonResult(ErrorCode errorCode, String msg) {
        super();
        this.success = false;
        if (errorCode != null) {
            this.errorCode = errorCode.getCode();
            this.errorMessage = errorCode.getMessage();
        } else {
            this.errorCode = 0;
            this.errorMessage = "";
        }
        if (msg != null) {
            if (!this.errorMessage.isEmpty()) {
                this.errorMessage += " ";
            }
            this.errorMessage += msg;
        }
    }

    @Override
    public String toString() {
        return JacksonUtil.toJSon(this);
    }

}