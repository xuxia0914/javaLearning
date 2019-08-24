package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * TinyURL is a URL shortening service where you enter a URL
 * such as https://leetcode.com/problems/design-tinyurl and it returns a short URL
 * such as http://tinyurl.com/4e9iAk.
 * Design the encode and decode methods for the TinyURL service.
 * There is no restriction on how your encode/decode algorithm should work.
 * You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
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
