package com.sangeng.domain;

public enum ErrorCode {
    PARAM_NULL_ERROR(1001, "参数不能为空"),
    PARAM_SKU_NULL_ERROR(1002, "商品主键不能为空"),
    PARAM_PRICE_NULL_ERROR(1003, "商品价格不能为空"),
    PARAM_STOCK_NULL_ERROR(1004, "商品库存不能为空"),
    PARAM_PRICE_ILLEGAL_ERROR(1005, "商品价格非法"),
    PARAM_STOCK_ILLEGAL_ERROR(1006, "商品库存非法"),
    ;

    private int code;
    private String message;


    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
