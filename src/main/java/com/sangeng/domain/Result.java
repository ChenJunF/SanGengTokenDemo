package com.sangeng.domain;

import lombok.Data;

@Data
public class Result {

    private Boolean success;

    private String msg;

    private int code;

    public static Result success() {
        Result result = new Result();
        result.setSuccess(Boolean.TRUE);
        return result;
    }

    public static Result fail(String msg) {
        Result result = new Result();
        result.setSuccess(Boolean.FALSE);
        result.setCode(200);
        result.setMsg(msg);
        return result;
    }

    public static Result failure(ErrorCode errorCode) {
        Result result = new Result();
        result.setSuccess(Boolean.FALSE);
        result.setMsg(errorCode.getMessage());
        result.setCode(errorCode.getCode());
        return result;
    }

    public boolean isSuccess() {
        return success;
    }
}
