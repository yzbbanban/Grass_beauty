package com.wangban.yzbbanban.grass_beauty.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;


import com.wangban.yzbbanban.grass_beauty.R;
import com.wangban.yzbbanban.grass_beauty.consat.Contast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YZBbanban on 16/6/9.
 */
public class DisplayUtil implements Contast {
    private Context context;
    private GridView gridView;
    private Map<String, SoftReference<Bitmap>> cache = new HashMap<String, SoftReference<Bitmap>>();
    private List<ImageLoadTask> tasks = new ArrayList<ImageLoadTask>();
    private ViewPager viewPager;
    private boolean isLoop = true;
    private Thread workThread;
    private int type;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_LOAD_BITMAP_SUCCESSS:
                    ImageLoadTask task = (ImageLoadTask) msg.obj;
                    Bitmap bitmap = task.bm;
                    ImageView imageView = null;
                    if (viewPager != null) {
                        imageView = (ImageView) viewPager.findViewWithTag(task.path);
                    }
                    if (gridView != null) {
                        imageView = (ImageView) gridView.findViewWithTag(task.path);

                    }

                    Log.i(TAG, "handleMessage: 1111111");
                    if (imageView != null) {
                        if (bitmap != null) {
                            imageView.setImageBitmap(bitmap);
                        } else {
                            imageView.setImageResource(R.drawable.katong);
                        }
                    }

                    break;


            }
        }
    };

    public DisplayUtil(Context context, ViewPager viewpager) {
        startThread();
        this.context = context;
        this.viewPager = viewpager;
    }

    public DisplayUtil(Context context, GridView gridView) {
        startThread();
        this.context = context;
        this.gridView = gridView;

    }

    private void startThread() {
        workThread = new Thread() {
            @Override
            public void run() {
                while (isLoop) {
                    if (!tasks.isEmpty()) {
                        ImageLoadTask task = tasks.remove(0);
                        String path = task.path;
                        Bitmap bitmap = loadBitmap(path);
                        task.bm = bitmap;
                        Message msg = Message.obtain();
                        msg.what = HANDLER_LOAD_BITMAP_SUCCESSS;
                        msg.obj = task;
                        handler.sendMessage(msg);
                        Log.i(TAG, "run: hello");
                    } else {
                        try {
                            synchronized (workThread) {
                                workThread.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        workThread.start();

    }

    public void mageDisplay(ImageView mianImg, String path, int type) {
        this.type = type;
        Log.w(TAG, "mageDisplay: " + type);
        SoftReference<Bitmap> ref = cache.get(path);
        if (ref != null) {
            Bitmap bitmap = ref.get();
            if (bitmap != null) {
                mianImg.setImageBitmap(bitmap);
                return;
            }

        }
        //Log.i(TAG, "mageDisplay: context: " + context.getCacheDir().getAbsolutePath());
        File file = new File(context.getCacheDir()
                , path);
        Bitmap bitmap = BitmapUtil.loadBitmap(file.getAbsolutePath());


        if (bitmap != null) {
            mianImg.setImageBitmap(bitmap);
            cache.put(path, new SoftReference<Bitmap>(bitmap));
            return;
        }

        mianImg.setTag(path);
        ImageLoadTask task = new ImageLoadTask();
        task.path = path;
        tasks.add(task);
        synchronized (workThread) {
            workThread.notify();
        }
        return;
    }

    public Bitmap loadBitmap(String path) {
        try {
            Bitmap bitmap=null;
            InputStream is = HttpUtil.get(path);
            if (type==1) {
                bitmap = BitmapUtil.loadBitmap(is, type, 125, 160);
            }else {
                bitmap = BitmapUtil.loadBitmap(is, type, 750, 1096);
            }
            cache.put(path, new SoftReference<Bitmap>(bitmap));
            String fileName = path;
            // Log.e(TAG, "loadBitmap: 天呢！！！！！");

            File file = new File(context.getCacheDir(), fileName);
            BitmapUtil.saveImage(file.getAbsolutePath(), bitmap);

            //Log.i(TAG, "loadBitmap: " + path);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 生产模式
     */
    class ImageLoadTask {
        String path;
        Bitmap bm;
    }


    public void stopThread() {
        isLoop = false;
        synchronized (workThread) {
            workThread.notify();
        }
        workThread = null;
    }


}
