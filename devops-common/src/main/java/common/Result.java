package common;

import java.io.Serializable;

/**
 * @author: liushuai
 * @date: 2020/7/7
 * @description：
 */
public class Result<T> implements Serializable {

    private T obj;
    private Boolean success = true;
    private Integer errorCode = 0;//防止空指针异常，0表示没有异常
    private String errorMessage = "";

    /**
     * Result对象默认构造函数
     */
    public Result() {
    }

    /**
     * Result对象构造函数
     *
     * @param obj 详见{@link T}
     */
    public Result(T obj) {
        super();
        this.obj = obj;
    }

    /**
     * Result对象构造函数
     *
     * @param obj     详见{@link T}
     * @param success 详见{@link Boolean}
     */
    public Result(T obj, Boolean success) {
        super();
        this.obj = obj;
        this.success = success;
    }

    /**
     * Result对象构造函数
     *
     * @param success 详见{@link Boolean}
     */
    public Result(Boolean success) {
        super();
        this.success = success;
    }

    /**
     * Result对象默认构造函数
     *
     * @param errorCode 详见{@link ErrorCode}
     */
    public Result(ErrorCode errorCode) {
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
    public Result(ErrorCode errorCode, String msg) {
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

    public T getObj() {
        return this.obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
