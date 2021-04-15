package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1813. 句子相似性 III
 * 一个句子是由一些单词与它们之间的单个空格组成，且句子的开头和结尾没有多余空格。
 * 比方说，"Hello World" ，"HELLO" ，"hello world hello world" 都是句子。
 * 每个单词都 只 包含大写和小写英文字母。
 * 如果两个句子 sentence1 和 sentence2 ，
 * 可以通过往其中一个句子插入一个任意的句子（可以是空句子）而得到另一个句子，
 * 那么我们称这两个句子是 相似的 。
 * 比方说，sentence1 = "Hello my name is Jane" 且 sentence2 = "Hello Jane" ，
 * 我们可以往 sentence2 中 "Hello" 和 "Jane" 之间插入 "my name is" 得到 sentence1 。
 * 给你两个句子 sentence1 和 sentence2 ，
 * 如果 sentence1 和 sentence2 是相似的，请你返回 true ，否则返回 false 。
 *
 * 示例 1：
 * 输入：sentence1 = "My name is Haley", sentence2 = "My Haley"
 * 输出：true
 * 解释：可以往 sentence2 中 "My" 和 "Haley" 之间插入 "name is" ，得到 sentence1 。
 *
 * 示例 2：
 * 输入：sentence1 = "of", sentence2 = "A lot of words"
 * 输出：false
 * 解释：没法往这两个句子中的一个句子只插入一个句子就得到另一个句子。
 *
 * 示例 3：
 * 输入：sentence1 = "Eating right now", sentence2 = "Eating"
 * 输出：true
 * 解释：可以往 sentence2 的结尾插入 "right now" 得到 sentence1 。
 *
 * 示例 4：
 * 输入：sentence1 = "Luky", sentence2 = "Lucccky"
 * 输出：false
 *
 * 提示：
 * 1 <= sentence1.length, sentence2.length <= 100
 * sentence1 和 sentence2 都只包含大小写英文字母和空格。
 * sentence1 和 sentence2 中的单词都只由单个空格隔开。
 */
public class SentenceSimilarityIII {

    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        if (sentence1.equals(sentence2)) return true;
        // 保证 s1 是最长的那个字符串数组
        if (sentence1.length() < sentence2.length()) return areSentencesSimilar(sentence2, sentence1);
        String[] s1 = sentence1.split(" ");
        String[] s2 = sentence2.split(" ");
        // 检查长度为 1 时的特殊情况
        if (s2.length == 1) return s2[0].equals(s1[0]) || s2[0].equals(s1[s1.length - 1]);

        // 从左往右遍历短句子 s2，检查是不是在右边插入新的句子
        int idx = 0;    // 用来遍历 s2 的下标，如果 s2[idx] == s1[idx] ，就一直往右移动
        while (idx < s2.length && s2[idx].equals(s1[idx])) idx++;
        // 如果遍历完了 s2，那么说明匹配到了
        if (idx == s2.length) return true;

        // 从右往左遍历短句子 s2，检查是不是在左边插入新的句子
        idx = s2.length - 1;
        // 偏移量是用来补齐 s1 中下标的
        int offset = s1.length - s2.length;
        while (idx >= 0 && s2[idx].equals(s1[offset + idx])) --idx;
        if (idx == -1) return true;

        // 检查是否是夹在中间，使用两套指针分别去遍历 s1 和 s2
        int l1 = 0, l2 = 0, r1 = s1.length - 1, r2 = s2.length - 1;
        while (l2 <= r2 && s1[l1].equals(s2[l2])) {
            l1++;
            l2++;
        }
        while (r2 >= 0 && s1[r1].equals(s2[r2])) {
            r1--;
            r2--;
        }
        // 如果两个指针相遇了，那么说明把 s2 检查完了，返回 true，否则 false
        return r2 == l2 - 1;
    }

}
