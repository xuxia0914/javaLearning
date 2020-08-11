package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 418. 屏幕可显示句子的数量
 * 给你一个 rows x cols 的屏幕和一个用 非空 的单词列表组成的句子，
 * 请你计算出给定句子可以在屏幕上完整显示的次数。
 *
 * 注意：
 * 一个单词不能拆分成两行。
 * 单词在句子中的顺序必须保持不变。
 * 在一行中 的两个连续单词必须用一个空格符分隔。
 * 句子中的单词总量不会超过 100。
 * 每个单词的长度大于 0 且不会超过 10。
 * 1 ≤ rows, cols ≤ 20,000.
 *
 * 示例 1：
 * 输入：rows = 2, cols = 8,
 * 句子 sentence = ["hello", "world"]
 * 输出：1
 * 解释：
 * hello---
 * world---
 * 字符 '-' 表示屏幕上的一个空白位置。
 *
 * 示例 2：
 * 输入：rows = 3, cols = 6,
 * 句子 sentence = ["a", "bcd", "e"]
 * 输出：2
 * 解释：
 * a-bcd-
 * e-a---
 * bcd-e-
 * 字符 '-' 表示屏幕上的一个空白位置。
 *
 * 示例 3：
 * 输入：rows = 4, cols = 5,
 * 句子 sentence = ["I", "had", "apple", "pie"]
 * 输出：1
 * 解释：
 * I-had
 * apple
 * pie-I
 * had--
 * 字符 '-' 表示屏幕上的一个空白位置。
 */
public class SentenceScreenFitting {

    public static void main(String[] args) {
        System.out.println(new SentenceScreenFitting().wordsTyping(
                new String[]{"I", "had", "apple", "pie"}, 4, 5
        ));
    }

    /**
     * @param sentence: a list of string
     * @param rows: an integer
     * @param cols: an integer
     * @return: return an integer, denote times the given sentence can be fitted on the screen
     */
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int ans = 0;
        if(rows<=0||cols<=0) {
            return ans;
        }
        int n = sentence.length;
        int idx = 0;
        int r = 0;
        int c = 0;
        while(r<rows) {
            if(cols-c<sentence[idx].length()) {
                if(c==0) {
                    return 0;
                }else {
                    r++;
                    c = 0;
                }
            }else {
                c += sentence[idx].length()+1;
                idx++;
                if(idx==n) {
                    ans++;
                    idx = 0;
                }
                if(c>=cols) {
                    r++;
                    c = 0;
                }
            }
        }
        return ans;
    }

}
