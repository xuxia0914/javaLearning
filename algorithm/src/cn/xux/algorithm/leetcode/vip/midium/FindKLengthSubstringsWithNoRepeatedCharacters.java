package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 1100：长度为 K 的无重复字符子串
 * 示例 1：
 * 输入：S = "havefunonleetcode", K = 5
 * 输出：6
 * 解释：这里有 6 个满足题意的子串，分别是：'havef','avefu','vefun','efuno','etcod','tcode'。
 *
 * 示例 2：
 * 输入：S = "home", K = 5
 * 输出：0
 * 解释：注意：K 可能会大于 S 的长度。在这种情况下，就无法找到任何长度为 K 的子串。
 *
 * 提示：
 * 1 <= S.length <= 10^4
 * S 中的所有字符均为小写英文字母
 * 1 <= K <= 10^4
 */
public class FindKLengthSubstringsWithNoRepeatedCharacters {

    public static void main(String[] args) {
        FindKLengthSubstringsWithNoRepeatedCharacters fl
                = new FindKLengthSubstringsWithNoRepeatedCharacters();
        System.out.println(fl.numKLenSubstrNoRepeats("havefunonleetcode", 5));
    }

    public int numKLenSubstrNoRepeats(String S, int K) {
        if(S==null||S.length()<K||K<1) {
            return 0;
        }
        int ans = 0;
        int left = 0;
        int[] cnts = new int[26];
        for(int right=0;right<S.length();right++) {
            int idx = S.charAt(right)-'a';
            cnts[idx]++;
            while(cnts[idx]>1||right-left+1>K) {
                cnts[S.charAt(left)-'a']--;
                left++;
            }
            if(right-left+1==K) {
                ans++;
            }
        }
        return ans;
    }

}
