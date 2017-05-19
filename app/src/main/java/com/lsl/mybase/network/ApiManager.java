package com.lsl.mybase.network;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * author:Created by lsl on 2017/5/18 09:42.
 * description:请求参数格式处理类
 */

public class ApiManager {

    /**
     * 请求参数格式设置为json
     *
     * @param json 访问的参数json
     * @return RequestBody
     */
    public static RequestBody toRequestBody(String  json) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        return body;
    }
}
