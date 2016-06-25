package com.wangban.yzbbanban.grass_beauty.model;



import android.os.AsyncTask;
import android.util.Log;

import com.wangban.yzbbanban.grass_beauty.consat.Contast;
import com.wangban.yzbbanban.grass_beauty.entity.Image;
import com.wangban.yzbbanban.grass_beauty.util.JsoupUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YZBbanban on 16/6/6.
 */
public class MainPictureDao implements Contast {

    public MainPictureDao() {
    }


    public void findImageGridView(final Callback callback, final String webPath) {
        AsyncTask<String, String, List<Image>> task = new AsyncTask<String, String, List<Image>>() {
            List<Image> images = new ArrayList<Image>();

            @Override
            protected List<Image> doInBackground(String... params) {
                try {
                    Log.i(TAG, "doInBackground: " + webPath);
                    images = JsoupUtil.downLoadData(webPath);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return images;
            }
            @Override
            protected void onPostExecute(List<Image> images) {
                callback.onImageDownload(images);
            }
        };
        task.execute();
    }

    public interface Callback {
        void onImageDownload(List<Image> list);
    }
}
