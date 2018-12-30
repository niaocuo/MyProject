package com.hang.up.util;

/**
 * Created by 顺 on 2018/12/8.
 */
public class BaseResult<T> {
    private String code;

    private T data;

    private String  message;

    public BaseResult() {
        this.setCode(ResultCode.SUCCESS);
        this.setMessage("成功！");
    }

    public BaseResult(ResultCode code) {
        this.setCode(code);
        this.setMessage(code.msg());
    }

    public BaseResult(ResultCode code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public BaseResult(ResultCode code, String message, T data) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code.val();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
