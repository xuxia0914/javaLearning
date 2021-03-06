package cn.xux.algorithm.leetcode.vip.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 247. 中心对称数 II（DP）
 *
 * 中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。
 *
 * 找到所有长度为 n 的中心对称数。
 *
 * 示例 :
 * 输入:  n = 2
 * 输出: ["11","69","88","96"]
 */
public class StrobogrammaticNumberII {

    public List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }

    public List<String> helper(int m, int n) {
        List<String> res = new ArrayList<>();
        if(m==0) {
            res.add("");
            return res;
        }
        if(m==1) {
            res.add("0");
            res.add("1");
            res.add("8");
            return res;
        }
        List<String> pre = helper(m-2, n);
        for(String s : pre) {
            if(m!=n) {
                res.add("0"+s+"0");
            }
            res.add("1"+s+"1");
            res.add("6"+s+"9");
            res.add("8"+s+"8");
            res.add("9"+s+"6");
        }
        return res;
    }

    public static void main(String[] args) {
        StrobogrammaticNumberII sn = new StrobogrammaticNumberII();
        System.out.println(sn.findStrobogrammatic(4));
    }

}
