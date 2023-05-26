package com.example.SpringBoot_webSocketChatting.Common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Integer SC_INTERNAL_SERVER_ERROR_500 = 500;
    private static final Integer SC_NORMAL_ERROR_505 = 505;
    private static final Integer SC_OK_200 = 200;
    private static final Integer SC_LOGIC_ERROR = 600;

    private boolean success = true;
    private String msg = "success";
    private Integer code = 0;
    private long timestamp = System.currentTimeMillis();
    private T result;
    private T data;

    public Result() {
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    public String getMessage() {
        return this.msg;
    }

    public void error500(String message) {
        this.msg = message;
        this.code = SC_INTERNAL_SERVER_ERROR_500;
        this.success = false;
    }

    public void error505(String message) {
        this.msg = message;
        this.code = SC_NORMAL_ERROR_505;
        this.success = false;
    }

    public void errorMsg(String message, int errorCode) {
        this.msg = message;
        this.code = errorCode;
        this.success = false;
    }

    public void errorLogic(String message) {
        this.msg = message;
        this.code = SC_LOGIC_ERROR;
        this.success = false;
    }

    public void success(String message) {
        this.msg = message;
        this.code = SC_OK_200;
        this.success = true;
    }

    public static Result<Object> error(String msg) {
        return error(SC_INTERNAL_SERVER_ERROR_500, msg);
    }

    public static Result<Object> error(int code, String message) {
        Result<Object> r = new Result<Object>();
        r.setCode(code);
        r.setMessage(message);
        r.setSuccess(false);
        return r;
    }

    public static Result<Object> ok(String message) {
        Result<Object> r = new Result<Object>();
        r.setSuccess(true);
        r.setCode(SC_OK_200);
        r.setMessage(message);
        return r;
    }

    public static Result<Object> ok(Object data) {
        Result<Object> r = new Result<Object>();
        r.setSuccess(true);
        r.setCode(SC_OK_200);
        r.setResult(data);
        return r;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

