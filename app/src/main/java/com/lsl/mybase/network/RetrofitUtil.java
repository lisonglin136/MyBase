package com.lsl.mybase.network;

import com.google.gson.Gson;
import com.lsl.mybase.global.NetUrl;
import com.lsl.mybase.model.ResponseModel;
import com.lsl.mybase.model.base.HeadBean;
import com.lsl.mybase.model.base.RequestHead;
import com.lsl.mybase.model.base.RequestModel;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitUtil {


    /**
     * 网络访问封装
     *
     * @param url      访问地址(不包含baseUrl)
     * @param T         requestBody
     * @param observer  observer
     */
    public static <T> void postNetData(String url, T requestBody, Observer<ResponseModel> observer) {
        RequestModel requestModel = new RequestModel();
        requestModel.setBody(requestBody);
        HeadBean headBean = RequestHead.getHeadBean(requestBody);
        requestModel.setBody(headBean);
        Gson gson = new Gson();
        String parmJson = gson.toJson(requestModel);
        RetrofitUtil.getApiService()
                .postNetwork(url, ApiManager.toRequestBody(parmJson))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 网络访问设置
     *
     * @return
     */
    public static ApiService getApiService() {

        //调节请求参数为json

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request build = request.newBuilder().addHeader("Content-Type",
                                "application/json")
                                .addHeader("Accept", "application/json")
                                .build();
                        return chain.proceed(build);
                    }
                })
                .addInterceptor(logging)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(NetUrl.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        //此方法不符合程序员思想故舍弃
//        Retrofit retrofit1 = new Retrofit.Builder()
//                .client(client)
//                .baseUrl(NetUrl.base_url)
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .build();

        ApiService mApiService = retrofit.create(ApiService.class);
        return mApiService;
    }


}
