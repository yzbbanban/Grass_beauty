package com.wangban.yzbbanban.grass_beauty.app;

import android.app.Application;

import com.wangban.yzbbanban.grass_beauty.consat.Contast;
import com.wangban.yzbbanban.grass_beauty.entity.DetialImage;

import java.util.ArrayList;

/**
 * Created by YZBbanban on 16/6/5.
 */
public class ImgApplication extends Application implements Contast {
    private ArrayList<DetialImage> images;
    private int position;
    @Override
    public void onCreate() {
        //to be continue;
    }

    public ArrayList<DetialImage> getImageData() {
        return images;
    }

    public void setImageData(ArrayList<DetialImage> images) {
        this.images = images;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


}
