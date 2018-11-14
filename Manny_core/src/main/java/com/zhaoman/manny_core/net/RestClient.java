package com.zhaoman.manny_core.net;

import android.content.Context;

import com.zhaoman.manny_core.net.callback.IError;
import com.zhaoman.manny_core.net.callback.IFailure;
import com.zhaoman.manny_core.net.callback.IRequest;
import com.zhaoman.manny_core.net.callback.ISuccess;
import com.zhaoman.manny_core.net.callback.RequestCallbacks;
import com.zhaoman.manny_core.net.download.DownloadHandler;
import com.zhaoman.manny_core.ui.loader.LoaderStyle;
import com.zhaoman.manny_core.ui.loader.MannyLoader;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;


import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class RestClient {

    private final String URL;
    private static final WeakHashMap<String,Object> PARAMS=RestCreator.getParams();
    private final IRequest REQUESET;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError   ERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYTLE;
    private final Context CONTEXT;
    private final File FILE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;


    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest requeset,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      LoaderStyle loaderStytle,
                      Context context,
                      File file,
                      String download_dir,
                      String extension,
                      String name) {
        this.URL = url;
        this.PARAMS.putAll(params);
        this.REQUESET = requeset;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.LOADER_STYTLE=loaderStytle;
        this.CONTEXT=context;
        this.FILE=file;
        this.DOWNLOAD_DIR=download_dir;
        this.EXTENSION=extension;
        this.NAME=name;
    }

    public static RestClientBuilder builder(){

        return new RestClientBuilder();
    }




    private void requset(HttpMethod method){

        final RestService service=RestCreator.getRestService();

        Call<String> call=null;

        if (REQUESET!=null){
            REQUESET.onRequestStart();
        }

        if (LOADER_STYTLE!=null){
            MannyLoader.showLoading(CONTEXT,LOADER_STYTLE);
        }


        switch (method){

            case GET:
                call=service.get(URL,PARAMS);
                break;
            case POST:
                call=service.post(URL,PARAMS);
                break;
            case PUT_RAW:
                call=service.postRaw(URL,BODY);
                break;
            case PUT:
                call=service.put(URL,PARAMS);
                break;
            case POST_RAW:
                call=service.putRaw(URL,BODY);
                break;
            case DELETE:
                call=service.delete(URL,PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody=
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body=
                        MultipartBody.Part.createFormData("file",FILE.getName());

                call=RestCreator.getRestService().upload(URL,body);
                break;

                default:
                    break;
        }



        if (call!=null){

            call.enqueue(getRequestCallback());
        }

    }



    private Callback<String> getRequestCallback(){

        return new RequestCallbacks(REQUESET,SUCCESS,FAILURE,ERROR,LOADER_STYTLE);
    }





    public final void get(){
        requset(HttpMethod.GET);
    }


    public final void post(){
        if (BODY==null){
            requset(HttpMethod.POST);
        }else{
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null");
            }
            requset(HttpMethod.POST_RAW);
        }

    }

    public final void put(){

        if (BODY==null){
            requset(HttpMethod.PUT);
        }else{
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null");
            }
            requset(HttpMethod.PUT_RAW);
        }

    }

    public final void delete(){
        requset(HttpMethod.DELETE);
    }

    public final void upload(){

        requset(HttpMethod.UPLOAD);
    }

    public final void download(){

        new DownloadHandler(URL,REQUESET,SUCCESS,FAILURE,ERROR,DOWNLOAD_DIR,EXTENSION,NAME)
                .handleDownload();
    }
}
