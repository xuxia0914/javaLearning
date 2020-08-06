package cn.xux.algorithm.leetcode.vip.midium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 244. 最短单词距离 II
 * 请设计一个类，使该类的构造函数能够接收一个单词列表。
 * 然后再实现一个方法，该方法能够分别接收两个单词 word1 和 word2，
 * 并返回列表中这两个单词之间的最短距离。您的方法将被以不同的参数调用 多次。
 *
 * 示例:
 * 假设 words = ["practice", "makes", "perfect", "coding", "makes"]
 * 输入: word1 = “coding”, word2 = “practice”
 * 输出: 3
 * 输入: word1 = "makes", word2 = "coding"
 * 输出: 1
 *
 * 注意:
 * 你可以假设 word1 不等于 word2, 并且 word1 和 word2 都在列表里。
 *
 * 思路：
 * 如果每次调用都暴力搜索是不行滴，因为最后一个TEST CASE会调用很多很多次，会超时。
 * 所以可以考虑初始化时建立hashmap， key是words里的每个单词，val是每个word出现的所有下标，
 * 这样调用shortest的时候，就只需要处理hashmap中word1和word2的记录即可。
 */
public class ShortestWordDistanceII {

    Map<String, List<Integer>> map = new HashMap<>();

    ShortestWordDistanceII(String[] words) {
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
        List<Integer> list2 = map.get(word1);
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
        return res;
    }

}
