package cn.xux.algorithm.leetcode.general.easy;

/**
 * 2062. 统计字符串中的元音子字符串
 * 子字符串 是字符串中的一个连续（非空）的字符序列。
 * <p>
 * 元音子字符串 是 仅 由元音（'a'、'e'、'i'、'o' 和 'u'）
 * 组成的一个子字符串，且必须包含 全部五种 元音。
 * <p>
 * 给你一个字符串 word ，统计并返回 word 中 元音子字符串的数目 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：word = "aeiouu"
 * 输出：2
 * 解释：下面列出 word 中的元音子字符串（斜体加粗部分）：
 * - "aeiouu"
 * - "aeiouu"
 * 示例 2：
 * <p>
 * 输入：word = "unicornarihan"
 * 输出：0
 * 解释：word 中不含 5 种元音，所以也不会存在元音子字符串。
 * 示例 3：
 * <p>
 * 输入：word = "cuaieuouac"
 * 输出：7
 * 解释：下面列出 word 中的元音子字符串（斜体加粗部分）：
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 * 示例 4：
 * <p>
 * 输入：word = "bbaeixoubb"
 * 输出：0
 * 解释：所有包含全部五种元音的子字符串都含有辅音，所以不存在元音子字符串。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= word.length <= 100
 * word 仅由小写英文字母组成
 */
public class CountVowelSubstringsOfAString {

    public int countVowelSubstrings(String word) {
        int n = word.length(), ans = 0;
        // 五个元音的状态
        int vowel = 1 | 1 << 4 | 1 << 8 | 1 << 14 | 1 << 20;
        for (int i = 0; i < n; ++i) {
            int mask = 0;
            for (int j = i; j < n; ++j) {
                char ch = word.charAt(j);
                // 碰到非元音
                if ((vowel & 1 << ch - 'a') == 0) break;
                mask |= 1 << ch - 'a';
                if (mask == vowel) ++ans;
            }
        }
        return ans;
    }

}
