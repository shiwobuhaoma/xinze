package com.xinze.xinze.http.function;

import com.google.gson.Gson;
import com.xinze.xinze.http.entity.HttpResponse;
import com.xinze.xinze.http.exception.ServerException;
import com.xinze.xinze.utils.LogUtils;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
/**
 * 服务器结果处理函数
 *
 * @author lxf
 */
public class ServerResultFunction implements Function<HttpResponse, Object> {
    @Override
    public Object apply(@NonNull HttpResponse response) throws Exception {
        //打印服务器回传结果
        LogUtils.e(response.toString());
        if (!response.isSuccess()) {
            throw new ServerException(response.getCode(), response.getMsg());
        }
        return new Gson().toJson(response.getResult());
    }
}
