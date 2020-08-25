package cn.xux.algorithm.leetcode.general.easy;

/**
 * 459. 重复的子字符串
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。
 * 给定的字符串只含有小写英文字母，并且长度不超过10000。
 *
 * 示例 1:
 * 输入: "abab"
 * 输出: True
 * 解释: 可由子字符串 "ab" 重复两次构成。
 *
 * 示例 2:
 * 输入: "aba"
 * 输出: False
 *
 * 示例 3:
 * 输入: "abcabcabcabc"
 * 输出: True
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 */
public class RepeatedSubstringPattern {

    public static void main(String[] args) {
        System.out.println(new RepeatedSubstringPattern().repeatedSubstringPattern("aabaaba"));
    }

    public boolean repeatedSubstringPattern(String s) {
        if(s==null||s.length()<2) {
            return false;
        }
        int len = s.length();
        String tmp;
        for(int i=1;i<=(int)Math.sqrt(len);i++) {
            if(len%i==0) {
                int j=i;
                tmp = s.substring(0,j);
                for(;j<len;j+=i) {
                    if(!tmp.equals(s.substring(j,j+i))) {
                        break;
                    }
                }
                if(j==len) {
                    return true;
                }
                if(i!=1) {
                    j = len/i;
                    tmp = s.substring(0, j);
                    for(;j<len;j+=len/i) {
                        if(!tmp.equals(s.substring(j,j+len/i))) {
                            break;
                        }
                    }
                    if(j==len) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
