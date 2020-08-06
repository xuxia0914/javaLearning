package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 373. 查找和最小的K对数字
 * 给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2。
 * 找到和最小的 k 对数字 (u1,v1), (u2,v2) ... (uk,vk)。
 *
 * 示例 1:
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 *      [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 *
 * 示例 2:
 * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 输出: [1,1],[1,1]
 * 解释: 返回序列中的前 2 对数：
 *      [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 *
 * 示例 3:
 * 输入: nums1 = [1,2], nums2 = [3], k = 3
 * 输出: [1,3],[2,3]
 * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
 */
public class FindKPairsWithSmallestSums {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums1==null||nums1.length==0||nums2==null||nums2.length==0||k<=0) {
            return res;
        }
        int n1 = nums1.length;
        int n2 = nums2.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> (nums1[o[0]] + nums2[o[1]])));
        for(int i=0;i<n1;i++) {
            queue.offer(new int[]{i,0});
        }
        k = Math.min(k, n1*n2);
        while(k-->0) {
            int[] curr = queue.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[curr[0]]);
            list.add(nums2[curr[1]]);
            res.add(list);
            if(curr[1]+1<n2) {
                queue.offer(new int[]{curr[0], curr[1]+1});
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FindKPairsWithSmallestSums fp = new FindKPairsWithSmallestSums();
        System.out.println(fp.kSmallestPairs(new int[]{1,7,11}, new int[]{2,4,6}, 9));
        System.out.println(fp.kSmallestPairs(new int[]{1,1,2}, new int[]{1,2,3}, 2));
        System.out.println(fp.kSmallestPairs(new int[]{1,2}, new int[]{3}, 3));
    }

}
