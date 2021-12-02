package cn.xux.algorithm.leetcode.general.midium;

/**
 * 423. 从英文中重建数字
 * 给你一个字符串 s ，其中包含字母顺序打乱的用英文单词表示的若干数字（0-9）。按 升序 返回原始的数字。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "owoztneoer"
 * 输出："012"
 * 示例 2：
 * <p>
 * 输入：s = "fviefuro"
 * 输出："45"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 105
 * s[i] 为 ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"] 这些字符之一
 * s 保证是一个符合题目要求的字符串
 */
public class ReconstructOriginalDigitsFromEnglish {

    public static void main(String[] args) {
        System.out.println(new ReconstructOriginalDigitsFromEnglish().originalDigits("esnve"));
    }

    public String originalDigits(String s) {
        // building hashmap letter -> its frequency
        char[] count = new char[26];
        for (char letter : s.toCharArray()) {
            count[letter - 'a']++;
        }

        // building hashmap digit -> its frequency
        int[] out = new int[10];
        // letter "z" is present only in "zero"
        out[0] = count[25];
        // letter "w" is present only in "two"
        out[2] = count[22];
        // letter "u" is present only in "four"
        out[4] = count[20];
        // letter "x" is present only in "six"
        out[6] = count[23];
        // letter "g" is present only in "eight"
        out[8] = count[6];
        // letter "h" is present only in "three" and "eight"
        out[3] = count[7] - out[8];
        // letter "f" is present only in "five" and "four"
        out[5] = count[5] - out[4];
        // letter "s" is present only in "seven" and "six"
        out[7] = count[18] - out[6];
        // letter "i" is present in "nine", "five", "six", and "eight"
        out[9] = count[8] - out[5] - out[6] - out[8];
        // letter "n" is present in "one", "nine", and "seven"
        out[1] = count[13] - out[7] - 2 * out[9];

        // building output string
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < out[i]; j++)
                output.append(i);
        return output.toString();
    }

}
