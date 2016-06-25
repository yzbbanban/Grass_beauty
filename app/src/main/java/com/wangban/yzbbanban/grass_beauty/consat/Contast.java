package com.wangban.yzbbanban.grass_beauty.consat;

import android.os.Environment;

/**
 * Created by YZBbanban on 16/6/5.
 */
public interface Contast {
    int HANDLER_PICDAL_SUCCESS = 1;

    String SEXY = "http://m.2cto.com/meinv/sexmv/";
    String BIKINI="http://m.2cto.com/meinv/rhmeinv/";
    String ATTRACT="http://m.2cto.com/meinv/gaoqing/";
    String MODEL="http://m.2cto.com/meinv/meitui/";
    String AUTODYNE="http://m.2cto.com/meinv/mote/";
    String YOUNG="http://m.2cto.com/meinv/xiaoqingxin/";
    String STAR="http://m.2cto.com/meinv/mingxing/";
    String RECOMMEND="http://m.2cto.com/meinv/top/";

    /**
     *   性感美女     http://www.2cto.com/meinv/sexmv/

     比基尼
     http://www.2cto.com/meinv/rhmeinv/

     美女诱惑
     http://www.2cto.com/meinv/gaoqing/

     性感车模
     http://www.2cto.com/meinv/meitui/

     美女自拍
     http://www.2cto.com/meinv/mote/

     清纯美女
     http://www.2cto.com/meinv/xiaoqingxin/

     明星图片
     http://www.2cto.com/meinv/mingxing/

     推荐图片
     http://www.2cto.com/meinv/top/
     * 性感美女sexy     比基尼bikini     美女诱惑attract     性感车模model
     * 美女自拍autodyne     清纯美女young     明星图片star     推荐图片recommend
     */

    String BEAUTY_PATH = Environment.getExternalStorageDirectory() + "/DCIM/";

    int MAX_SIZE = 250;

    String TAG = "supergirl";
    String TAG2 = "superman";

    int HANDLER_LOAD_BITMAP_SUCCESSS = 1;
    int HANDLER_SAVE_IMGER_TO_SD = 3;
    int HANDLER_SAVE_START=6;
    int HANDLER_SET_WALLPAPER=8;
    int REFRESH_COMPLETE = 2;
    int TOUCH_STOP = 50;
    int TOUCH_MOVE=150;
    int TOUCH_FINISH=150;
    int TOUCH_MOVE_X=70;
    int TOUCH_MOVE_Y=70;
    int MOVE_TO_RIGHT=4;
    int MOVE_TO_LEFT=5;
    int FINISH_IMG=7;

    String EXTRA_PATH = "path";
    String EXTRA_IMAGE_PATH = "image_path";
    String EXTRA_IMAGE_POSITION="image_position";
   // String EXTRA_IMAGES="images";

}
