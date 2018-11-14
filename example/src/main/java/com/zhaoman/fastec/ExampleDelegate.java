package com.zhaoman.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zhaoman.manny_core.app.ConfigType;
import com.zhaoman.manny_core.app.Manny;
import com.zhaoman.manny_core.delegates.MannyDelegate;
import com.zhaoman.manny_core.net.RestClient;
import com.zhaoman.manny_core.net.callback.IError;
import com.zhaoman.manny_core.net.callback.IFailure;
import com.zhaoman.manny_core.net.callback.IRequest;
import com.zhaoman.manny_core.net.callback.ISuccess;

public class ExampleDelegate extends MannyDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegaste_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        testRestClient();


    }


    private void testRestClient(){

        RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                        Log.d("TAG",response);

                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(),"失败了",Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String message) {
                        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
                    }
                })
                .request(new IRequest() {
                    @Override
                    public void onRequestStart() {

                    }

                    @Override
                    public void onRequestEnd() {

                    }
                })
                .build().get();

    }
}

