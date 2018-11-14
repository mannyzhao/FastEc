package com.zhaoman.manny_ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.zhaoman.manny_core.delegates.MannyDelegate;
import com.zhaoman.manny_core.net.RestClient;
import com.zhaoman.manny_core.net.callback.ISuccess;
import com.zhaoman.manny_core.wechat.MannyWeChat;
import com.zhaoman.manny_core.wechat.callbacks.IWXChatSignInCallback;
import com.zhaoman.manny_ec.R;
import com.zhaoman.manny_ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author:zhaoman
 * Date:2018/11/12
 * Description:
 */
public class SignInDelegate extends MannyDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    private ISignListener mISignListener=null;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){

            mISignListener=(ISignListener) activity;
        }
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {

        if (checkForm()){

            RestClient.builder()
                    .url("http://192.168.3.170:8080/userprofile.json")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {

                            Log.d("TAG",response);
                            SignHandler.onSignIn(response,mISignListener);
                        }
                    }).build().post();
        }

    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickLink(){
        MannyWeChat.getInstance().onSignSuccess(new IWXChatSignInCallback() {
            @Override
            public void onSignInSuccess(String userInfo) {

            }
        }).signIn();

    }


    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }
}
