package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1234. 替换子串得到平衡字符串
 * 有一个只含有 'Q', 'W', 'E', 'R' 四种字符，且长度为 n 的字符串。
 * 假如在该字符串中，这四个字符都恰好出现 n/4 次，那么它就是一个「平衡字符串」。
 * 给你一个这样的字符串 s，请通过「替换一个子串」的方式，使原字符串 s 变成一个「平衡字符串」。
 * 你可以用和「待替换子串」长度相同的 任何 其他字符串来完成替换。
 * 请返回待替换子串的最小可能长度。
 * 如果原字符串自身就是一个平衡字符串，则返回 0。
 *
 * 示例 1：
 * 输入：s = "QWER"
 * 输出：0
 * 解释：s 已经是平衡的了。
 *
 * 示例 2：
 * 输入：s = "QQWE"
 * 输出：1
 * 解释：我们需要把一个 'Q' 替换成 'R'，这样得到的 "RQWE" (或 "QRWE") 是平衡的。
 *
 * 示例 3：
 * 输入：s = "QQQW"
 * 输出：2
 * 解释：我们可以把前面的 "QQ" 替换成 "ER"。
 *
 * 示例 4：
 * 输入：s = "QQQQ"
 * 输出：3
 * 解释：我们可以替换后 3 个 'Q'，使 s = "QWER"。
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * s.length 是 4 的倍数
 * s 中只含有 'Q', 'W', 'E', 'R' 四种字符
 */
public class ReplaceTheSubstringForBalancedString {

    public int balancedString(String s) {
        int len = s.length();
        int[] cnts = new int[4];
        for(char c : s.toCharArray()) {
            cnts[c=='Q'?0:(c=='W'?1:(c=='E'?2:3))]++;
        }
        int target = len/4;
        int[] needs = new int[26];
        for(int i=0;i<4;i++) {
            needs[i] = Math.max(0, cnts[i]-target);
        }
        if(needs[0]==0&&needs[1]==0&&needs[2]==0&&needs[3]==0) {
            return 0;
        }
        int left = 0;
        int right = -1;
        int[] curr = new int[4];
        int result = len;
        while(right<len) {
            while((curr[0]<needs[0]||curr[1]<needs[1]
                    ||curr[2]<needs[2]||curr[3]<needs[3])&&++right<len) {
                char cr = s.charAt(right);
                curr[cr=='Q'?0:(cr=='W'?1:(cr=='E'?2:3))]++;
            }
            while(left<=right&&curr[0]>=needs[0]&&curr[1]>=needs[1]
                    &&curr[2]>=needs[2]&&curr[3]>=needs[3]) {
                char cl = s.charAt(left);
                curr[cl=='Q'?0:(cl=='W'?1:(cl=='E'?2:3))]--;
                left++;
            }
            result = Math.min(result, right-left+2);
        }
        return result;
    }

}
