package cn.xux.algorithm.leetcode.vip.midium;

import java.util.HashSet;
import java.util.Set;

/**
 * 1554. 只有一个不同字符的字符串
 * 给定一个字符串列表 dict ，其中所有字符串的长度都相同。
 * 当存在两个字符串在相同索引处只有一个字符不同时，返回 True ，否则返回 False 。
 * 进阶：你可以以 O(n*m) 的复杂度解决问题吗？其中 n 是列表 dict 的长度，m 是字符串的长度。
 *
 * 示例 1：
 * 输入：dict = [“abcd”,“acbd”, “aacd”]
 * 输出：true
 * 解释：字符串 “abcd” 和 “aacd” 只在索引 1 处有一个不同的字符。
 *
 * 示例 2：
 * 输入：dict = [“ab”,“cd”,“yz”]
 * 输出：false
 *
 * 示例 3：
 * 输入：dict = [“abcd”,“cccc”,“abyd”,“abab”]
 * 输出：true
 */
public class StringsDifferByOneCharacter {

    public boolean differByOne(String[] dict) {
        if(dict==null||dict.length<2) {
            return false;
        }
        Set<String> set;
        for(int i=0;i<dict[0].length();i++) {
            set = new HashSet<>();
            for(String word : dict) {
                if(!set.add(word.substring(0,i)+word.substring(i+1))) {
                    return true;
                }
            }
        }
        return false;
    }

}
