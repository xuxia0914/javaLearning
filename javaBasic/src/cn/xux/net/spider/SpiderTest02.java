package cn.xux.net.spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SpiderTest02 {

    public static void main(String[] args) {
        try {
            // 获取url
            // URL url = new URL("https://www.jd.com");
            URL url = new URL("https://www.dianping.com");
            // 下载资源
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Mobile Safari/537.36");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));
            String msg = null;
            while(null!=(msg=br.readLine())) {
                System.out.println(msg);
            }
            // 分析
            // 处理...
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
