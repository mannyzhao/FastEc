package com.zhaoman.manny_core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.zhaoman.manny_core.app.Manny;
import com.zhaoman.manny_core.net.callback.IRequest;
import com.zhaoman.manny_core.net.callback.ISuccess;
import com.zhaoman.manny_core.utils.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

public class SaveFileTask extends AsyncTask<Object,Void,File> {


    private final IRequest REQUESET;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest request, ISuccess success) {
        this.REQUESET = request;
        this.SUCCESS = success;
    }

    @Override
    protected File doInBackground(Object... params) {

        String downLoadDir=(String) params[0];
        String extension= (String) params[1];
        final ResponseBody body= (ResponseBody) params[2];
        final String name= (String) params[3];
        final InputStream is=body.byteStream();
        if (downLoadDir==null||downLoadDir.equals("")){
            downLoadDir="down_loads";
        }

        if (extension==null||extension.equals("")){
            extension="";
        }
        if (name==null){

            return FileUtil.writeToDisk(is,downLoadDir,extension.toUpperCase(),extension);
        }else{

            return FileUtil.writeToDisk(is,downLoadDir,name);
        }

    }


    /**
     * 下载完成之后
     * @param file
     */

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);

        if (SUCCESS!=null){
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUESET!=null){
            REQUESET.onRequestEnd();
        }

    }


    private void autoInstallApk(File file){
        //如果后缀名是apk 的文件
        if (FileUtil.getExtension(file.getPath()).equals("apk")){

            final Intent install=new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            Manny.getApplication().startActivity(install);
        }
    }


}
