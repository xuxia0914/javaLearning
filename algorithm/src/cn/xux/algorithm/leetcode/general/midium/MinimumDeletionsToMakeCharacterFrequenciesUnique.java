package cn.xux.algorithm.leetcode.general.midium;

import java.util.*;

/**
 * 1647. 字符频次唯一的最小删除次数
 * 如果字符串 s 中 不存在 两个不同字符 频次 相同的情况，就称 s 是 优质字符串 。
 * 给你一个字符串 s，返回使 s 成为 优质字符串 需要删除的 最小 字符数。
 * 字符串中字符的 频次 是该字符在字符串中的出现次数。
 * 例如，在字符串 "aab" 中，'a' 的频次是 2，而 'b' 的频次是 1 。
 *
 * 示例 1：
 * 输入：s = "aab"
 * 输出：0
 * 解释：s 已经是优质字符串。
 *
 * 示例 2：
 * 输入：s = "aaabbbcc"
 * 输出：2
 * 解释：可以删除两个 'b' , 得到优质字符串 "aaabcc" 。
 * 另一种方式是删除一个 'b' 和一个 'c' ，得到优质字符串 "aaabbc" 。
 *
 * 示例 3：
 * 输入：s = "ceabaacb"
 * 输出：2
 * 解释：可以删除两个 'c' 得到优质字符串 "eabaab" 。
 * 注意，只需要关注结果字符串中仍然存在的字符。（即，频次为 0 的字符会忽略不计。）
 *
 * 提示：
 * 1 <= s.length <= 105
 * s 仅含小写英文字母
 */
public class MinimumDeletionsToMakeCharacterFrequenciesUnique {

    public int minDeletions(String s) {
        int[] a = new int[26];
        char[] cs = s.toCharArray();
        for (char c : cs) {
            a[c - 'a'] ++;// 统计字母个数
        }

        Set<Integer> h = new HashSet<Integer>();
        int res = 0;
        for (int i : a) {
            if (i != 0) {               // 有数目才进行判断
                while (h.contains(i)) { // set已经包含就自减
                    i --;
                    res ++;
                }
                if (i != 0) {
                    h.add(i);   // 自减到0时，表示完全删除了某个字母，不能加入set中
                }
            }
        }
        return res;
    }

    public int minDeletions1(String s) {
        if(s==null||s.length()<2) {
            return 0;
        }
        int[] cnts = new int[26];
        for(char c : s.toCharArray()) {
            cnts[c-'a']++;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>((o1,o2)->o2-o1);
        for(int cnt : cnts) {
            if(cnt>0) {
                map.put(cnt, map.getOrDefault(cnt, 0)+1);
            }
        }
        int ans = 0;
        while(map.size()>0) {
            Map.Entry<Integer, Integer> entry = map.pollFirstEntry();
            int key = entry.getKey();
            int value = entry.getValue();
            int nextKey = map.size()==0?0:map.firstKey();
            if(key-nextKey>=value) {
                ans += value*(value-1)/2;
            }else {
                ans += (key-nextKey)*(key-nextKey-1)/2;
                int leave = value-key+nextKey;
                ans += leave*(key-nextKey);
                if(nextKey!=0) {
                    map.put(nextKey, map.get(nextKey)+leave);
                }
            }
        }
        return ans;
    }

}
