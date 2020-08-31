package cn.xux.algorithm.leetcode.vip.midium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 666. 路径和 IV
 * 对于一棵深度小于 5 的树，可以用一组三位十进制整数来表示。
 *
 * 对于每个整数：
 * 百位上的数字表示这个节点的深度 D，1 <= D <= 4。
 * 十位上的数字表示这个节点在当前层所在的位置 P， 1 <= P <= 8。位置编号与一棵满二叉树的位置编号相同。
 * 个位上的数字表示这个节点的权值 V，0 <= V <= 9。
 * 给定一个包含三位整数的升序数组，表示一棵深度小于 5 的二叉树，
 * 请你返回从根到所有叶子结点的路径之和。
 *
 * 样例 1:
 * 输入: [113, 215, 221]
 * 输出: 12
 * 解释:
 * 这棵树形状如下:
 *     3
 *    / \
 *   5   1
 * 路径和 = (3 + 5) + (3 + 1) = 12.
 *
 *
 * 样例 2:
 * 输入: [113, 221]
 * 输出: 4
 * 解释:
 * 这棵树形状如下:
 *     3
 *      \
 *       1
 * 路径和 = (3 + 1) = 4.
 */
public class PathSumIV {

    /**
     * @param nums: the list
     * @return: the sum of all paths from the root towards the leaves
     */
    public int pathSumIV(int[] nums) {
        // Write your code here.
        if(nums==null||nums.length==0) {
            return 0;
        }
        Arrays.sort(nums);
        Map<Integer, Integer> cnts = new HashMap<>();
        int ans = 0;
        for(int i=nums.length-1;i>=0;i--) {
            int key = nums[i]/10;
            int value = nums[i]%10;
            int leftKey = (key/10+1)*10+key%10*2-1;
            int rightKey = leftKey+1;
            if(!cnts.containsKey(leftKey)&&!cnts.containsKey(rightKey)) {
                ans += value;
                cnts.put(key, 1);
            }else {
                int cnt = cnts.getOrDefault(leftKey, 0)
                        + cnts.getOrDefault(rightKey, 0);
                ans += cnt *value;
                cnts.put(key, cnt);
            }
        }
        return ans;
    }

}
