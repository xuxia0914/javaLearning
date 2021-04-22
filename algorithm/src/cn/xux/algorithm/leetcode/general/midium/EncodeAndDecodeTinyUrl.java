package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 535. TinyURL 的加密与解密
 * TinyURL是一种URL简化服务，
 * 比如：当你输入一个URL https://leetcode.com/problems/design-tinyurl 时，
 * 它将返回一个简化的URL http://tinyurl.com/4e9iAk.
 * 要求：设计一个 TinyURL 的加密 encode 和解密 decode 的方法。
 * 你的加密和解密算法如何设计和运作是没有限制的，你只需要保证一个URL可以被加密成一个TinyURL，
 * 并且这个TinyURL可以用解密方法恢复成原本的URL。
 */
public class EncodeAndDecodeTinyUrl {

    public List<String> tinyUrls = new ArrayList<String>();
    public List<String> longUrls = new ArrayList<String>();

    public String s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if(longUrls.contains(longUrl)) {
            return "http://tinyurl.com/" + tinyUrls.get(longUrls.indexOf(longUrl));
        }
        longUrls.add(longUrl);
        String tinyUrl = "";
        while("".equals(tinyUrl)||tinyUrls.contains(tinyUrl)) {
            StringBuffer sb = new StringBuffer();
            Random random = new Random();
            for(int i=0;i<6;i++) {
                sb.append(s.charAt(random.nextInt(52)));
            }
            tinyUrl = sb.toString();
        }
        tinyUrls.add(tinyUrl);
        return "http://tinyurl.com/" + tinyUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String tinyUrl) {
        String[] ss = tinyUrl.split("/");
        String tiny = ss[ss.length-1];
        if(!tinyUrls.contains(tiny)) {
            return "";
        }
        return longUrls.get(tinyUrls.indexOf(ss[ss.length-1]));
    }

    public static void main(String[] args) {
        EncodeAndDecodeTinyUrl eai = new EncodeAndDecodeTinyUrl();
        String tinyUrl = eai.encode("https://leetcode.com/problems/design-tinyurl");
        System.out.println(tinyUrl);
        System.out.println(eai.decode(tinyUrl));
    }

}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
