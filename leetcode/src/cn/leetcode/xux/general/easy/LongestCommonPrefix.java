package cn.leetcode.xux.general.easy;

/**
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 *
 * 示例 2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 *
 * 说明:
 * 所有输入只包含小写字母 a-z 。
 */
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if(strs==null||strs.length==0) {
            return "";
        }
        int len = Integer.MAX_VALUE;
        for(String str : strs) {
            len = Math.min(len, str.length());
        }
        int end = 0;
        for(;end<len;end++) {
            boolean flag = true;
            for(int i=1;i<strs.length;i++) {
                if(strs[i].charAt(end)!=strs[i-1].charAt(end)) {
                    flag = false;
                    break;
                }
            }
            if(!flag) {
                break;
            }
        }
        return strs[0].substring(0, end);
    }

}
