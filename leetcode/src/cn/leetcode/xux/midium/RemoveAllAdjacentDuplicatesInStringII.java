package cn.leetcode.xux.midium;

import javafx.util.Pair;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 1209. 删除字符串中的所有相邻重复项 II
 * 给你一个字符串 s，「k 倍重复项删除操作」将会从 s 中选择 k 个相邻且相等的字母，
 * 并删除它们，使被删去的字符串的左侧和右侧连在一起。
 * 你需要对 s 重复进行无限次这样的删除操作，直到无法继续为止。
 * 在执行完所有删除操作后，返回最终得到的字符串。
 * 本题答案保证唯一。
 *
 * 示例 1：
 * 输入：s = "abcd", k = 2
 * 输出："abcd"
 * 解释：没有要删除的内容。
 *
 * 示例 2：
 * 输入：s = "deeedbbcccbdaa", k = 3
 * 输出："aa"
 * 解释：
 * 先删除 "eee" 和 "ccc"，得到 "ddbbbdaa"
 * 再删除 "bbb"，得到 "dddaa"
 * 最后删除 "ddd"，得到 "aa"
 *
 * 示例 3：
 * 输入：s = "pbbcggttciiippooaais", k = 2
 * 输出："ps"
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * 2 <= k <= 10^4
 * s 中只含有小写英文字母。
 */
public class RemoveAllAdjacentDuplicatesInStringII {

    public static void main(String[] args) {
        System.out.println(new RemoveAllAdjacentDuplicatesInStringII()
                .removeDuplicates("deeedbbcccbdaa", 3));
    }

    public String removeDuplicates(String s, int k) {
        Deque<int[]> deque = new LinkedList<>();
        for(char c : s.toCharArray()) {
            if(deque.isEmpty()||deque.peekLast()[0]!=c) {
                deque.offerLast(new int[]{c, 1});
            }else if(deque.peekLast()[1]==k-1) {
                deque.pollLast();
            }else {
                deque.peekLast()[1]++;
            }
        }
        StringBuilder result = new StringBuilder();
        while(!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            while(curr[1]-->0) {
                result.append((char)curr[0]);
            }
        }
        return result.toString();
    }

}
