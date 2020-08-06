package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1291. 顺次数
 * 我们定义「顺次数」为：每一位上的数字都比前一位上的数字大 1 的整数。
 * 请你返回由 [low, high] 范围内所有顺次数组成的 有序 列表（从小到大排序）。
 *
 * 示例 1：
 * 输出：low = 100, high = 300
 * 输出：[123,234]
 *
 * 示例 2：
 * 输出：low = 1000, high = 13000
 * 输出：[1234,2345,3456,4567,5678,6789,12345]
 *
 * 提示：
 * 10 <= low <= high <= 10^9
 */
public class SequentialDigits {

    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<9;i++) {
            int curr = i;
            while(curr<high&&curr%10<9) {
                curr += curr*10+curr%10+1;
                if(curr>=low&&curr<=high) {
                    result.add(curr);
                }
            }
        }
        Collections.sort(result);
        return result;
    }

}
