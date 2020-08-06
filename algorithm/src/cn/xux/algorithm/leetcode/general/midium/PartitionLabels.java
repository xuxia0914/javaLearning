package cn.xux.algorithm.leetcode.general.midium;

import java.util.LinkedList;
import java.util.List;

/**
 * 763. 划分字母区间
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。
 * 返回一个表示每个字符串片段的长度的列表。
 *
 * 示例 1:
 * 输入: S = "ababcbacadefegdehijhklij"
 * 输出: [9,7,8]
 * 解释:
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 *
 * 注意:
 * S的长度在[1, 500]之间。
 * S只包含小写字母'a'到'z'。
 */
public class PartitionLabels {

    public List<Integer> partitionLabels(String S) {
        List<Integer> result = new LinkedList<>();
        int[] lasts = new int[26];
        for(int i=0;i<S.length();i++) {
            lasts[S.charAt(i)-'a'] = i;
        }
        int start = 0;
        int end = 0;
        for(int i=0;i<S.length();i++) {
            end = Math.max(end, lasts[S.charAt(i)-'a']);
            if(end==i) {
                result.add(end-start+1);
                start = i+1;
            }
        }
        return result;
    }

}
