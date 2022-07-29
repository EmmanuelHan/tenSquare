package com.tensquare.common.entity;


import java.util.HashMap;
import java.util.Map;

public class Result {

    private Integer code;

    private String message;

    private Object data;

    public Result() {
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(Object... data) {
        this.code = 20000;
        this.data = data;
        this.message = "成功";
    }

    public Result(Object data) {
        this.code = 20000;
        this.data = data;
        this.message = "成功";
    }

    public Result(ResultInterface resultEnum){
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
        this.data = new HashMap<>();
    }

    public Result(IPage page){
        Map<String, Object> data = new HashMap<>();
        data.put("pageSize", page.getSize());
        data.put("total", page.getTotal());
        data.put("pageNo", page.getCurrent());
        data.put("list", page.getRecords());
        this.code = 20000;
        this.data = data;
        this.message = "成功";
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
