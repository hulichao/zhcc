package com.hu.zhcc.common.entity;


import com.baidu.unbiz.fluentvalidator.ValidationError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 页面与后端交互api数据格式,
 *
 * Created by hulichao on 2018/7/3
 */
public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public Result() {
        put("state", 0);
    }

    public static Result success(){
        return new Result();
    }

    public static Result success(String msg){
        Result r = new Result();
        r.put("msg", msg);
        return r;
    }

    public static Result success(Map<String, Object> map){
        Result r = new Result();
        r.putAll(map);
        return r;
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static Result fail(){
        Result r = new Result();
        r.fail(500, "后台异常，请联系管理员");
        return r;
    }

    public static Result fail(String msg){
        Result r = new Result();
        r.put("msg", msg);
        return r;
    }

    public static Result fail(List<ValidationError> errors){
        return fail(errors.get(0).getErrorMsg()).put("data",errors);
    }
    public static Result fail(int state, String msg){
        Result r = new Result();
        r.put("state", state);
        r.put("msg", msg);
        return r;
    }
}
