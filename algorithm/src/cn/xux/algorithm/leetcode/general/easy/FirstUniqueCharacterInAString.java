package cn.xux.algorithm.leetcode.general.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 387. 字符串中的第一个唯一字符
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。
 * 如果不存在，则返回 -1。
 *
 * 示例：
 * s = "leetcode"
 * 返回 0
 * s = "loveleetcode"
 * 返回 2
 *
 * 提示：你可以假定该字符串只包含小写字母。
 */
public class FirstUniqueCharacterInAString {

    public int firstUniqChar(String s) {
        if(s==null||s.length()==0) {
            return -1;
        }
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i=0;i<s.length();i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1);
        }
        for(int i=0;i<s.length();i++) {
            if(map.get(s.charAt(i))==1) {
                return i;
            }
        }
        return -1;
    }

    public int firstUniqChar1(String s) {
        if(s==null||s.length()==0) {
            return -1;
        }
        int[] array = new int[26];
        for(int i=0;i<s.length();i++) {
            array[s.charAt(i)-'a']++;
        }
        for(int i=0;i<s.length();i++) {
            if(array[s.charAt(i)-'a']==1) {
                return i;
            }
        }
        return -1;
    }

}
