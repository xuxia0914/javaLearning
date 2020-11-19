package cn.xux.net.spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class SpiderTest01 {

    public static void main(String[] args) {
        try {
            // 获取url
            URL url = new URL("https://www.jd.com");
            // URL url = new URL("https://www.dianping.com");
            // 下载资源
            InputStream is = url.openStream();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(is, "utf-8"));
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
