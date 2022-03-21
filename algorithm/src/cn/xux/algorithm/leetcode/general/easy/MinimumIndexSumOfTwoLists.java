package cn.xux.algorithm.leetcode.general.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 599. 两个列表的最小索引总和
 * 假设Andy和Doris想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
 * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。
 * 你可以假设总是存在一个答案。
 * <p>
 * 示例 1:
 * 输入:
 * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 * ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
 * 输出: ["Shogun"]
 * 解释: 他们唯一共同喜爱的餐厅是“Shogun”。
 * <p>
 * 示例 2:
 * 输入:
 * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 * ["KFC", "Shogun", "Burger King"]
 * 输出: ["Shogun"]
 * 解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
 * <p>
 * 提示:
 * 两个列表的长度范围都在 [1, 1000]内。
 * 两个列表中的字符串的长度将在[1，30]的范围内。
 * 下标从0开始，到列表的长度减1。
 * 两个列表都没有重复的元素。
 */
public class MinimumIndexSumOfTwoLists {

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> index = new HashMap<String, Integer>();
        for (int i = 0; i < list1.length; i++) {
            index.put(list1[i], i);
        }

        List<String> ret = new ArrayList<String>();
        int indexSum = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++) {
            if (index.containsKey(list2[i])) {
                int j = index.get(list2[i]);
                if (i + j < indexSum) {
                    ret.clear();
                    ret.add(list2[i]);
                    indexSum = i + j;
                } else if (i + j == indexSum) {
                    ret.add(list2[i]);
                }
            }
        }
        return ret.toArray(new String[ret.size()]);
    }

    public String[] findRestaurant1(String[] list1, String[] list2) {
        int m = list1.length;
        int n = list2.length;
        int maxIdx = 2000;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n && j <= maxIdx - i; j++) {
                if (list1[i].equals(list2[j])) {
                    if (i + j < maxIdx) {
                        res.clear();
                        maxIdx = i + j;
                    }
                    res.add(list1[i]);
                }
            }
        }
        return res.toArray(new String[res.size()]);
    }

    public static void main(String[] args) {
        MinimumIndexSumOfTwoLists mo = new MinimumIndexSumOfTwoLists();
//        System.out.println(mo.findRestaurant(new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"},
//                new String[]{"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"}));
//        System.out.println(mo.findRestaurant(new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"},
//                new String[]{"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"}));
        System.out.println(mo.findRestaurant(new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"},
                new String[]{"KFC", "Burger King", "Tapioca Express", "Shogun"}));
    }

}
