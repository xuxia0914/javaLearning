package cn.xux.algorithm.leetcode.general.easy;

/**
 * 171. Excel表列序号
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 *
 * 例如，
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
 * 示例 1:
 *
 * 输入: "A"
 * 输出: 1
 * 示例 2:
 *
 * 输入: "AB"
 * 输出: 28
 * 示例 3:
 *
 * 输入: "ZY"
 * 输出: 701
 */
public class ExcelSheetColumnNumber {

    public int titleToNumber(String columnTitle) {
        int ans = 0;
        for(char c : columnTitle.toCharArray()) {
            ans = ans*26+c-'A'+1;
        }
        return ans;
    }

}
