package com.zhaoman.manny_core.net;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface RestService {


    /**
     * Get 请求
     * @param url
     * @param params
     * @return
     */
    @GET
    Call<String> get(@Url String url, @QueryMap Map<String,Object> params);


    /**
     * Post 请求
     * @param url
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String,Object> params);


    @POST
    Call<String> postRaw(@Url String url, @Body RequestBody body);


    /**
     * Put 请求
     * @param url
     * @param params
     * @return
     */
    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap Map<String,Object> params);



    @PUT
    Call<String> putRaw(@Url String url, @Body RequestBody body);


    /**
     * Delete 请求
     * @param url
     * @param params
     * @return
     */
    @DELETE
    Call<String> delete(@Url String url, @QueryMap Map<String,Object> params);


    /**
     * 下载操作
     * @param url
     * @param params
     * @return
     */
    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap Map<String,Object> params);


    /**
     * 上传操作
     * @param url
     * @param file
     * @return
     */
    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);


}
