package cn.leetcode.xux.general.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 424. 替换后的最长重复字符
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。
 * 在执行上述操作后，找到包含重复字母的最长子串的长度。
 * 注意:
 * 字符串长度 和 k 不会超过 104。
 * 示例 1:
 *
 * 输入:s = "ABAB", k = 2
 * 输出:4
 * 解释:用两个'A'替换为两个'B',反之亦然。
 *
 * 示例 2:
 * 输入:s = "AABABBA", k = 1
 * 输出:4
 * 解释:将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。子串 "BBBB" 有最长重复字母, 答案为 4。
 */
public class LongestRepeatingCharacterReplacement {

    public static void main(String[] args) {
        System.out.println(new LongestRepeatingCharacterReplacement().characterReplacement("AAAA", 2));
    }

    public int characterReplacement(String s, int k) {
        if(s==null||s.length()==0) {
            return 0;
        }
        int len = s.length();
        int[] cnts = new int[26];
        int left = 0;
        int max = 0;
        for(int right=0;right<len;right++) {
            char c = s.charAt(right);
            cnts[c-'A']++;
            max = Math.max(max, cnts[c-'A']);
            if(right-left+1>max+k) {
                cnts[s.charAt(left++)-'A']--;
            }
        }
        return len-left;
    }

    public int characterReplacement1(String s, int k) {
        if(s==null||s.length()==0) {
            return 0;
        }
        int len = s.length();
        if(k>=len-1) {
            return len;
        }
        List<int[]>[] mem = new List[26];
        char curr = s.charAt(0);
        int start = 0;
        for(int i=1;i<len;i++) {
            if(s.charAt(i)!=curr) {
                if(mem[curr-'A']==null) {
                    mem[curr-'A'] = new ArrayList<>();
                }
                mem[curr-'A'].add(new int[]{start, i-1});
                start = i;
                curr = s.charAt(i);
            }
        }
        if(mem[curr-'A']==null) {
            mem[curr-'A'] = new ArrayList<>();
        }
        mem[curr-'A'].add(new int[]{start, len-1});
        int result = 0;
        for(List<int[]> list : mem) {
            if(list!=null) {
                for(int i=0;i<list.size();i++) {
                    int tmp = k;
                    int[] span = list.get(i);
                    int right = span[1];
                    int left = span[0];
                    for(int j=i-1;j>=0;j--) {
                        int[] preSpan = list.get(j);
                        if(left-preSpan[1]-1<=tmp) {
                            tmp -= left-preSpan[1]-1;
                            left = preSpan[0];
                        }else {
                            break;
                        }
                    }
                    int currResult = right-left+1;
                    currResult = Math.min(len, currResult+tmp);
                    result = Math.max(result, currResult);
                }
            }
        }
        return result;
    }

}
