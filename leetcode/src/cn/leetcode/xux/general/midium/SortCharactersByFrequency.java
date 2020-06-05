package cn.leetcode.xux.general.midium;

import java.util.*;

/**
 * 451. 根据字符出现频率排序
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 *
 * 示例 1:
 * 输入:"tree"
 * 输出:"eert"
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 *
 * 示例 2:
 *
 * 输入:"cccaaa"
 * 输出:"cccaaa"
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 *
 * 示例 3:
 * 输入:"Aabb"
 * 输出:"bbAa"
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 */
public class SortCharactersByFrequency {

    public String frequencySort(String s) {
        if(s==null||s.length()<3) {
            return s;
        }
        Map<Character, Integer> map = new HashMap<>();
        for(char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        String[] strs = new String[map.size()];
        int idx = 0;
        for(char c : map.keySet()) {
            StringBuilder sb = new StringBuilder();
            int len = map.get(c);
            while(len-->0) {
                sb.append(c);
            }
            strs[idx++] = sb.toString();
        }
        Arrays.sort(strs, (o1, o2)->o2.length()-o1.length());
        StringBuilder result = new StringBuilder();
        for(String str : strs) {
            result.append(str);
        }
        return result.toString();
    }

}
