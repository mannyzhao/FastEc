package com.zhaoman.manny_core.net.download;

import android.os.AsyncTask;

import com.zhaoman.manny_core.net.RestCreator;
import com.zhaoman.manny_core.net.callback.IError;
import com.zhaoman.manny_core.net.callback.IFailure;
import com.zhaoman.manny_core.net.callback.IRequest;
import com.zhaoman.manny_core.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadHandler {

    private final String URL;
    private static final WeakHashMap<String,Object> PARAMS=RestCreator.getParams();
    private final IRequest REQUESET;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;


    public DownloadHandler(String url,
                           IRequest requeset,
                           ISuccess success,
                           IFailure failure,
                           IError error,
                           String download_dir,
                           String extension,
                           String name) {
        this.URL = url;
        this.REQUESET = requeset;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
    }

    public final void handleDownload(){

        if (REQUESET!=null){
            REQUESET.onRequestStart();
        }
        RestCreator.getRestService()
                .download(URL,PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        if (response.isSuccessful()){
                            final ResponseBody responseBody = response.body();
                            final SaveFileTask saveFileTask = new SaveFileTask(REQUESET, SUCCESS);
                            saveFileTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,EXTENSION,responseBody,NAME);

                            //这里一定要注意判断 否则文件下载不全
                            if (saveFileTask.isCancelled()){
                                if (REQUESET!=null){
                                    REQUESET.onRequestEnd();
                                }
                            }
                        }else{
                            if (ERROR!=null){
                                ERROR.onError(response.code(),response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        if (FAILURE!=null){
                            FAILURE.onFailure();
                        }
                    }
                });

    }
}
