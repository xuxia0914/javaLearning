package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 245. 最短单词距离 III
 * 给定一个单词列表和两个单词 word1 和 word2，返回列表中这两个单词之间的最短距离。
 * word1 和 word2 是有可能相同的，并且它们将分别表示为列表中两个独立的单词。
 *
 * 示例:
 * 假设 words = ["practice", "makes", "perfect", "coding", "makes"].
 * 输入: word1 = “makes”, word2 = “coding”
 * 输出: 1
 * 输入: word1 = "makes", word2 = "makes"
 * 输出: 3
 *
 * 注意:
 * 你可以假设 word1 和 word2 都在列表里。
 *
 * 思路：
 * 分类讨论，
 * 如果word1!= word2那么和之前的题目一模一样，
 * 如果相等，那么就用一个变量记录上一次出现的下表即可。
 */
public class ShortestWordDistanceIII {

    Map<String, List<Integer>> map = new HashMap<>();

    ShortestWordDistanceIII(String[] words) {
        for(int i=0;i<words.length;i++) {
            if(map.containsKey(words[i])) {
                map.get(words[i]).add(i);
            }else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(words[i], list);
            }
        }
    }

    public int shortestDistance(String word1, String word2) {
        int res = Integer.MAX_VALUE;
        List<Integer> list1 = map.get(word1);
        if(word1.equals(word2)) {
            for(int i=0;i<list1.size()-1;i++) {
                res = Math.min(res, list1.get(i+1)-list1.get(i));
            }
        }else {
            List<Integer> list2 = map.get(word2);
            int idx1 = 0;
            int idx2 = 0;
            while(idx1<list1.size()&&idx2<list2.size()) {
                if(list1.get(idx1)>list2.get(idx2)) {
                    res = Math.min(res, list1.get(idx1)-list2.get(idx2));
                    idx1++;
                }else {
                    res = Math.min(res, list2.get(idx2)-list1.get(idx1));
                    idx2++;
                }
            }
        }
        return res;
    }

}
