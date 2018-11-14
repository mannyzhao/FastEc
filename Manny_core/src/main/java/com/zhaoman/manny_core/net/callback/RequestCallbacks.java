package com.zhaoman.manny_core.net.callback;

import android.os.Handler;

import com.zhaoman.manny_core.ui.loader.LoaderStyle;
import com.zhaoman.manny_core.ui.loader.MannyLoader;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestCallbacks implements Callback<String> {

    private final IRequest REQUESET;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError   ERROR;
    private final LoaderStyle LOADER_STYTLE;
    private static final Handler HANDLER=new Handler();

    public RequestCallbacks(IRequest requeset, ISuccess success, IFailure failure, IError error,LoaderStyle style) {
        this.REQUESET = requeset;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYTLE=style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {

        if (response.isSuccessful()){

            if (call.isExecuted()){

                if (SUCCESS!=null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else{

            if (ERROR!=null){
                ERROR.onError(response.code(),response.message());
            }
        }

        stopLoading();

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

        if (FAILURE!=null){
            FAILURE.onFailure();
        }

        if (REQUESET!=null){

            REQUESET.onRequestEnd();
        }
        stopLoading();
    }



    private void stopLoading(){

        if (LOADER_STYTLE!=null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MannyLoader.stopLoading();
                }
            },1000);
        }


    }

}
