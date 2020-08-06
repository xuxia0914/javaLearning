package cn.xux.algorithm.leetcode.general.easy;

/**
 * 1189. “气球” 的最大数量
 * 给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
 * 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
 *
 * 示例 1：
 * 输入：text = "nlaebolko"
 * 输出：1
 *
 * 示例 2：
 * 输入：text = "loonbalxballpoon"
 * 输出：2
 *
 * 示例 3：
 * 输入：text = "leetcode"
 * 输出：0
 *
 * 提示：
 * 1 <= text.length <= 10^4
 * text 全部由小写英文字母组成
 */
public class MaximumNumberOfBalloons {

    public static void main(String[] args) {
        System.out.println(new MaximumNumberOfBalloons().maxNumberOfBalloons("nlaebolko"));
    }

    public int maxNumberOfBalloons(String text) {
        int[] cnts = new int[26];
        for(char c : text.toCharArray()) {
            cnts[c-'a']++;
        }
        int result = Integer.MAX_VALUE;
        int[][] pairs = new int[][]{{0,1},{1,1},{11,2},{13,1},{14,2}};
        for(int[] pair : pairs) {
            result = Math.min(result, cnts[pair[0]]/pair[1]);
        }
        return result;
    }

}
