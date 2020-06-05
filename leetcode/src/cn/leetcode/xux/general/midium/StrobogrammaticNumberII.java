package cn.leetcode.xux.general.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 247 中心对称数 II
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Find all strobogrammatic numbers that are of length = n.
 * For example,
 * Given n = 2, return ["11","69","88","96"].
 * Hint:
 * Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
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
