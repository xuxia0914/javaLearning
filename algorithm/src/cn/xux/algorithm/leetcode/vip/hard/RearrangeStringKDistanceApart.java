package cn.xux.algorithm.leetcode.vip.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 358. K 距离间隔重排字符串
 * 给你一个非空的字符串 s 和一个整数 k，你要将这个字符串中的字母进行重新排列，
 * 使得重排后的字符串中相同字母的位置间隔距离至少为 k。
 * 所有输入的字符串都由小写字母组成，如果找不到距离至少为 k 的重排结果，请返回一个空字符串 ""。
 *
 * 示例 1：
 * 输入: s = "aabbcc", k = 3
 * 输出: "abcabc"
 * 解释: 相同的字母在新的字符串中间隔至少 3 个单位距离。
 *
 * 示例 2:
 * 输入: s = "aaabc", k = 3
 * 输出: ""
 * 解释: 没有办法找到可能的重排结果。
 *
 * 示例 3:
 * 输入: s = "aaadbbcc", k = 2
 * 输出: "abacabcd"
 * 解释: 相同的字母在新的字符串中间隔至少 2 个单位距离。
 */
public class RearrangeStringKDistanceApart {

    public static void main(String[] args) {
        RearrangeStringKDistanceApart rs = new RearrangeStringKDistanceApart();
//        System.out.println(rs.rearrangeString("aabbcc", 3));
//        System.out.println(rs.rearrangeString("aaabc", 3));
        System.out.println(rs.rearrangeString("aaadbbcc", 2));
    }

    public String rearrangeString(String str, int k) {
        if(str==null||str.length()==0||k<1) {
            return "";
        }
        int len = str.length();
        int[] cnts = new int[26];
        for(char c : str.toCharArray()) {
            cnts[c-'a']++;
        }
        int maxCnt = 0;
        for(int cnt : cnts) {
            maxCnt = Math.max(maxCnt, cnt);
        }
        if(len-maxCnt<(maxCnt-1)*(k-1)) {
            return "";
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1,o2)->o1[1]==o2[1]?o1[0]-o2[0]:o2[1]-o1[1]);
        for(int i=0;i<26;i++) {
            if(cnts[i]>0) {
                queue.offer(new int[]{i, cnts[i]});
            }
        }
        StringBuilder ans = new StringBuilder();
        while(!queue.isEmpty()) {
            List<int[]> tmp = new ArrayList<>();
            int a = Math.min(k, queue.size());
            while(a-->0) {
                int[] curr = queue.poll();
                ans.append((char)(curr[0]+'a'));
                if(curr[1]>1) {
                    curr[1]--;
                    tmp.add(curr);
                }
            }
            for(int[] curr : tmp) {
                queue.offer(curr);
            }
        }
        return ans.toString();
    }

}
