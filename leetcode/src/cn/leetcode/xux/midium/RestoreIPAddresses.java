package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string containing only digits,
 * restore it by returning all possible valid IP address combinations.
 * Example:
 * Input: "25525511135"
 * Output: ["255.255.11.135", "255.255.111.35"]
 */
public class RestoreIPAddresses {

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        helper(res, "", s, 1);
        return res;
    }

    public void helper(List<String> res, String curr, String s, int n) {
        if(n==5) {
            if(s.length()==0) {
                res.add(curr.substring(1));
            }
            return;
        }
        int len = s.length();
        if((5-n)*3<len||5-n>len) {
            return;
        }
        if(len>0) {
            String next1 = curr + '.' + s.substring(0,1);
            helper(res, next1, s.substring(1), n+1);
        }
        if(len>1&&!s.startsWith("0")) {
            String next2 = curr + '.' + s.substring(0,2);
            helper(res, next2, s.substring(2), n+1);
        }
        if(len>2&&!s.startsWith("0")&&Integer.valueOf(s.substring(0,3))<=255) {
            String next3 = curr + '.' + s.substring(0,3);
            helper(res, next3, s.substring(3), n+1);
        }
    }

}
