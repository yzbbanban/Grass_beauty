package com.wangban.yzbbanban.grass_beauty.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by YZBbanban on 16/6/7.
 */
public class HttpUtil {
    public static InputStream get(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        InputStream is = conn.getInputStream();
        return is;
    }



}
