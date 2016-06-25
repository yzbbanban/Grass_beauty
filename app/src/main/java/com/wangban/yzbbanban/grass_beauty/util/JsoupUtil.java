package com.wangban.yzbbanban.grass_beauty.util;

import android.util.Log;

import com.wangban.yzbbanban.grass_beauty.consat.Contast;
import com.wangban.yzbbanban.grass_beauty.entity.DetialImage;
import com.wangban.yzbbanban.grass_beauty.entity.Image;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YZBbanban on 16/6/9.
 */
public class JsoupUtil implements Contast {
    /**
     * 网页beauty类图片链接等解析
     * @param url
     * @return
     * @throws IOException
     */
    public static List<Image> downLoadData(String url) throws IOException {
        List<Image> images = new ArrayList<Image>();
        Document doc = Jsoup.connect(url).get();
        Elements e1 = doc.getElementsByClass("col-2");
        for (int i = 0; i < e1.size(); i++) {
            Elements a = e1.get(i).getElementsByTag("a");
            String skipPagePath=a.first().attr("href");
            String path2 = a.first().getElementsByTag("img").attr("src");
            String title=a.first().text();

            // Log.i(TAG, "run: " + "path2  " + path2 + "\n" + "width " + width2 + "\nheight " + height2);
            Image img = new Image();
            img.setSetSkipPagePath(skipPagePath);
            img.setPath(path2);
            img.setTitle(title);
            images.add(img);
            //Log.i(TAG, "getData: path:" + i + "; " + img.getPath());
            //Log.i(TAG, "getData: skppath:" + i + "; " + img.getSkipPagePath());
        }
        return images;
    }

    /**
     * 详细图片解析
     * @param url
     * @return
     * @throws IOException
     */
    public static List<DetialImage> downDetilLoadData(String url) throws IOException {
        List<DetialImage> images = new ArrayList<DetialImage>();
        Document doc = Jsoup.connect(url).get();
        Elements el = doc.getElementsByClass("article");
        String title=el.first().getElementsByTag("h1").text();
        Elements img=el.first().getElementsByTag("img");
        for (int i = 0; i < img.size(); i++) {
            String path = img.get(i).attr("src");
            DetialImage image = new DetialImage();
            image.setDetPath(path);
            image.setDetTitle(title);
            images.add(image);
            //Log.i(TAG, "downDetilLoadData: "+"\npath: "+path+"\ntitle: "+title);
        }
        return images;
    }

}
