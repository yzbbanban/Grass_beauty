package com.wangban.yzbbanban.grass_beauty.model;


import android.os.AsyncTask;

import com.wangban.yzbbanban.grass_beauty.consat.Contast;
import com.wangban.yzbbanban.grass_beauty.entity.DetialImage;
import com.wangban.yzbbanban.grass_beauty.util.JsoupUtil;

import java.io.IOException;
import java.util.*;

/**
 * Created by YZBbanban on 16/6/6.
 */
public class DetialPictureDao implements Contast {
    public DetialPictureDao() {

    }


    public void findDetilImageGridView(final Callback callback, final String webPath) {
        AsyncTask<String, String, List<DetialImage>> task = new AsyncTask<String, String, List<DetialImage>>() {
            List<DetialImage> images = new ArrayList<DetialImage>();

            @Override
            protected List<DetialImage> doInBackground(String... params) {
                try {
                    //Log.i(TAG, "doInBackground: hello" + webPath);
                    images = JsoupUtil.downDetilLoadData(webPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return images;
            }
            @Override
            protected void onPostExecute(List<DetialImage> images) {
                callback.onImageDownload(images);
            }
        };
        task.execute();
    }

    public interface Callback {
        void onImageDownload(List<DetialImage> list);
    }


}
