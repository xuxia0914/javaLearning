package cn.xux.algorithm.leetcode.general.midium;

/**
 * 2182. 构造限制重复的字符串
 * 给你一个字符串 s 和一个整数 repeatLimit ，
 * 用 s 中的字符构造一个新字符串 repeatLimitedString ，
 * 使任何字母 连续 出现的次数都不超过 repeatLimit 次。
 * 你不必使用 s 中的全部字符。
 *
 * 返回 字典序最大的 repeatLimitedString 。
 *
 * 如果在字符串 a 和 b 不同的第一个位置，
 * 字符串 a 中的字母在字母表中出现时间比字符串 b 对应的字母晚，
 * 则认为字符串 a 比字符串 b 字典序更大 。
 * 如果字符串中前 min(a.length, b.length) 个字符都相同，
 * 那么较长的字符串字典序更大。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "cczazcc", repeatLimit = 3
 * 输出："zzcccac"
 * 解释：使用 s 中的所有字符来构造 repeatLimitedString "zzcccac"。
 * 字母 'a' 连续出现至多 1 次。
 * 字母 'c' 连续出现至多 3 次。
 * 字母 'z' 连续出现至多 2 次。
 * 因此，没有字母连续出现超过 repeatLimit 次，字符串是一个有效的 repeatLimitedString 。
 * 该字符串是字典序最大的 repeatLimitedString ，所以返回 "zzcccac" 。
 * 注意，尽管 "zzcccca" 字典序更大，但字母 'c' 连续出现超过 3 次，所以它不是一个有效的 repeatLimitedString 。
 * 示例 2：
 *
 * 输入：s = "aababab", repeatLimit = 2
 * 输出："bbabaa"
 * 解释：
 * 使用 s 中的一些字符来构造 repeatLimitedString "bbabaa"。
 * 字母 'a' 连续出现至多 2 次。
 * 字母 'b' 连续出现至多 2 次。
 * 因此，没有字母连续出现超过 repeatLimit 次，字符串是一个有效的 repeatLimitedString 。
 * 该字符串是字典序最大的 repeatLimitedString ，所以返回 "bbabaa" 。
 * 注意，尽管 "bbabaaa" 字典序更大，但字母 'a' 连续出现超过 2 次，所以它不是一个有效的 repeatLimitedString 。
 *
 *
 * 提示：
 *
 * 1 <= repeatLimit <= s.length <= 105
 * s 由小写英文字母组成
 */
public class ConstructStringWithRepeatLimit {

    public String repeatLimitedString(String s, int repeatLimit) {
        int[] cnt = new int[26];
        for(char c : s.toCharArray()) {
            cnt[c-'a']++;
        }
        StringBuilder ans = new StringBuilder();
        int curr = 25;
        while(curr>=0&&cnt[curr]==0) {
            curr--;
        }
        int next = curr-1;
        while(next>=0&&cnt[next]==0) {
            next--;
        }
        int p = 0;
        while(curr>=0) {
            ans.append((char)(curr+'a'));
            if(--cnt[curr]==0) {
                curr = next--;
                while(next>=0&&cnt[next]==0) {
                    next--;
                }
                p = 0;
                continue;
            }
            if(++p==repeatLimit) {
                if(next<0) {
                    return ans.toString();
                }else {
                    ans.append((char)(next+'a'));
                    cnt[next]--;
                    while(next>=0&&cnt[next]==0) {
                        next--;
                    }
                }
                p = 0;
            }
        }
        return ans.toString();
    }

}
