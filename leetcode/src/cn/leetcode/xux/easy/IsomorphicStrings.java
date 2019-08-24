package cn.leetcode.xux.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 205. 同构字符串
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 *
 * 示例 1:
 * 输入: s = "egg", t = "add"
 * 输出: true
 *
 * 示例 2:
 * 输入: s = "foo", t = "bar"
 * 输出: false
 *
 * 示例 3:
 * 输入: s = "paper", t = "title"
 * 输出: true
 *
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度。
 */
public class IsomorphicStrings {

    public boolean isIsomorphic(String s, String t) {
        if(s==null) {
            return false;
        }
        if(s.length()<2) {
            return true;
        }
        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();
        for(int i=0;i<s.length();i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);
            if(map1.containsKey(a)) {
                if(map1.get(a)!=b) {
                    return false;
                }
            }else {
                map1.put(a, b);
            }
            if(map2.containsKey(b)) {
                if(map2.get(b)!=a) {
                    return false;
                }
            }else {
                map2.put(b, a);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new IsomorphicStrings().isIsomorphic("ab", "aa"));
    }

}
