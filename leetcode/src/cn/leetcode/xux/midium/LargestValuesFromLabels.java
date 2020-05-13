package cn.leetcode.xux.midium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 1090. 受标签影响的最大值
 * 我们有一个项的集合，其中第 i 项的值为 values[i]，标签为 labels[i]。
 * 我们从这些项中选出一个子集 S，这样一来：
 * |S| <= num_wanted
 * 对于任意的标签 L，子集 S 中标签为 L 的项的数目总满足 <= use_limit。
 * 返回子集 S 的最大可能的 和。
 *
 * 示例 1：
 * 输入：values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
 * 输出：9
 * 解释：选出的子集是第一项，第三项和第五项。
 *
 * 示例 2：
 * 输入：values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit = 2
 * 输出：12
 * 解释：选出的子集是第一项，第二项和第三项。
 *
 * 示例 3：
 * 输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 1
 * 输出：16
 * 解释：选出的子集是第一项和第四项。
 *
 * 示例 4：
 * 输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 2
 * 输出：24
 * 解释：选出的子集是第一项，第二项和第四项。
 *
 * 提示：
 * 1 <= values.length == labels.length <= 20000
 * 0 <= values[i], labels[i] <= 20000
 * 1 <= num_wanted, use_limit <= values.length
 */
public class LargestValuesFromLabels {

    //[5,4,3,2,1]
    //[1,1,2,2,3]
    //3
    //1
    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        int len = values.length;
        int[][] mapping = new int[len][2];
        for(int i=0;i<len;i++) {
            mapping[i][0] = values[i];
            mapping[i][1] = labels[i];
        }
        Arrays.sort(mapping, Comparator.comparingInt(o->-o[0]));
        int result = 0;
        int cnt = 0;
        Map<Integer, Integer> mem = new HashMap<>();
        for(int i=0;i<len;i++) {
            int currUsedCnt = mem.getOrDefault(mapping[i][1], 0);
            if(currUsedCnt<use_limit) {
                result += mapping[i][0];
                mem.put(mapping[i][1], currUsedCnt+1);
                if(++cnt==num_wanted) {
                    break;
                }
            }
        }
        return result;
    }

}
