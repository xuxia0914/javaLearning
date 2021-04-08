package cn.xux.algorithm.leetcode.general.hard;

import java.util.Arrays;

/**
 * 1707. 与数组中元素的最大异或值
 * 给你一个由非负整数组成的数组 nums 。
 * 另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。
 * 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。
 * 换句话说，答案是 max(nums[j] XOR xi) ，其中所有 j 均满足 nums[j] <= mi 。
 * 如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。
 * 返回一个整数数组 answer 作为查询的答案，
 * 其中 answer.length == queries.length 且 answer[i] 是第 i 个查询的答案。
 *
 * 示例 1：
 * 输入：nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
 * 输出：[3,3,7]
 * 解释：
 * 1) 0 和 1 是仅有的两个不超过 1 的整数。0 XOR 3 = 3 而 1 XOR 3 = 2 。二者中的更大值是 3 。
 * 2) 1 XOR 2 = 3.
 * 3) 5 XOR 2 = 7.
 *
 * 示例 2：
 * 输入：nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
 * 输出：[15,-1,5]
 *
 * 提示：
 * 1 <= nums.length, queries.length <= 105
 * queries[i].length == 2
 * 0 <= nums[j], xi, mi <= 109
 */
public class MaximumXorWithAnElementFromArray {

    //输入：
    //[536870912,0,534710168,330218644,142254206]
    //[[558240772,1000000000],[307628050,1000000000],[3319300,1000000000],[2751604,683297522],[214004,404207941]]
    //输出：
    //[1050219420,844498962,540190212,539622516,534529132]
    //预期结果：
    //[1050219420,844498962,540190212,539622516,330170208]

    //[5,2,4,6,6,3]
    //[[12,4],[8,1],[6,3]]
    //[15,-1,4]
    //[15,-1,5]
    public static void main(String[] args) {
        int[] ans = new MaximumXorWithAnElementFromArray().maximizeXor(
                new int[]{5,2,4,6,6,3},
                new int[][]{{12,4},{8,1},{6,3}});
        for(int a : ans) {
            System.out.print(a+", ");
        }
    }

    public int[] maximizeXor(int[] nums, int[][] queries) {
        Tree root = new Tree();
        for(int num : nums) {
            insert(root, num);
        }
        int[] ans = new int[queries.length];
        for(int i=0;i<ans.length;i++) {
            ans[i] = search(root, queries[i][0], 1<<30, queries[i][1], 0);
        }
        return ans;
    }

    private int search(Tree curr, int x, int n, int m, int min) {
        if(n==0) {
            return x^curr.val;
        }
        if(min+n<=m&&(curr.left==null||((n&x)==0&&curr.right!=null))) {
            int rightAns = search(curr.right, x, n>>1, m, min+n);
            if(rightAns!=-1) {
                return rightAns;
            }else if(curr.left==null) {
                return -1;
            }else {
                return search(curr.left, x, n>>1, m, min);
            }
        }else if(curr.left==null) {
            return -1;
        }else {
            return search(curr.left, x, n>>1, m, min);
        }
    }

    private void insert(Tree root, int num) {
        int n = 1<<30;
        Tree curr = root;
        while(n>0) {
            if((num&n)==0) {
                if(curr.left==null) {
                    curr.left = new Tree();
                }
                curr = curr.left;
            }else {
                if(curr.right==null) {
                    curr.right=  new Tree();
                }
                curr = curr.right;
            }
            n >>= 1;
        }
        curr.val = num;
    }

    class Tree {

        int val = -1;
        // 下一位0
        Tree left;
        // 下一位1
        Tree right;

    }

    // 暴力解法 TLE
    public int[] maximizeXor1(int[] nums, int[][] queries) {
        int n = queries.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for(int i=0;i<n;i++) {
            for(int num : nums) {
                if(num<=queries[i][1]) {
                    ans[i] = Math.max(ans[i], queries[i][0]^num);
                }
            }
        }
        return ans;
    }

}