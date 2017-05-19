package com.lsl.mybase.network;

import com.lsl.mybase.model.ResponseModel;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * author:Created by lsl on 2017/5/17 14:35.
 * description:访问方法参数
 */

public interface ApiService {

    @POST("{url}")
    Observable<ResponseModel> postNetwork(@Path("url") String url,@Body RequestBody jsonParams);




}
