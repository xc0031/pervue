package com.caoyuan.xiao4.pervue.vo;

import lombok.Data;

import java.io.Serializable;

/*********************************************************
 @author 曹原
 @date 2020/3/3 11:19 
 *********************************************************/
@Data
public class Result implements Serializable {

    //返回码
    private Integer code;

    //返回说明("成功","失败")
    private String reason;

    //返回的数据
    private Object result;

    //默认正确,ajax都是调用这个
    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(0);
        result.setReason("成功");
        result.setResult(data);
        return result;
    }

    //默认正确,用于传送正确值0
    public static Result success() {
        return success(null);
    }

    //失败的话,从捕获的之前抛出的异常中获取异常码和信息塞到这个方法中
    public static Result error(Integer code, String reason) {
        Result result = new Result();
        result.setCode(code);
        result.setReason(reason);
        return result;
    }
}