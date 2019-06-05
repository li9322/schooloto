package com.li.dto;

/**
 * @ClassName: Result
 * @Description: 封装json对象，所有返回结果都使用它
 * @author: libl
 * @date: 2019/06/05 10:38
 */
public class Result<T> {

    // 是否成功的标识
    private boolean success;

    // 成功时返回的数据
    private T data;

    // 错误码
    private int errorCode;

    // 错误信息
    private String errMsg;

    /**
     * @Description:空的构造函数
     */
    public Result() {
        super();
    }

    /**
     * @Description:数据获取成功时使用的构造器
     * @Param:success
     * @Param:data
     */
    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    /**
     * @Description:数据获取失败时使用的构造器
     * @Param:success
     * @Param:errorCode
     * @Param:errMsg
     */
    public Result(boolean success, int errorCode, String errMsg) {
        this.success = success;
        this.errorCode = errorCode;
        this.errMsg = errMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}

