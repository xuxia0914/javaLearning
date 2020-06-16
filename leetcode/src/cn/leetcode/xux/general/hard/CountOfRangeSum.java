package cn.leetcode.xux.general.hard;

/**
 * 327. 区间和的个数
 * 给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 *
 * 说明:
 * 最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。
 *
 * 示例:
 * 输入: nums = [-2,5,-1], lower = -2, upper = 2,
 * 输出: 3
 * 解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。
 */
public class CountOfRangeSum {

    public static void main(String[] args) {
        //[2147483647,-2147483648,-1,0]
        //-1
        //0
        System.out.println(new CountOfRangeSum().countRangeSum(
                new int[]{2147483647,-2147483648,-1,0}, -1, 0
        ));
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] preSums = new long[n+1];
        for(int i=1;i<=n;i++) {
            preSums[i] = preSums[i-1]+nums[i-1];
        }
        TreeNode1 root = new TreeNode1(0, 1, 0, 0);
        int ans = 0;
        for(int i=1;i<=n;i++) {
            ans += i-countSmaller(root, preSums[i]-upper)
                    -countBigger(root, preSums[i]-lower);
            insert(root, new TreeNode1(preSums[i], 1, 0, 0));
        }
        return ans;
    }

    private void insert(TreeNode1 root, TreeNode1 node) {
        if(node.val<root.val) {
            root.leftCount++;
            if(root.left==null) {
                root.left = node;
            }else {
                insert(root.left, node);
            }
        }else if(node.val==root.val) {
            root.count++;
        }else {
            root.rightCount++;
            if(root.right==null) {
                root.right = node;
            }else {
                insert(root.right, node);
            }
        }
    }

    private int countSmaller(TreeNode1 node, long target) {
        if(node==null) {
            return 0;
        }
        if(node.val<target) {
            return node.count+node.leftCount+countSmaller(node.right, target);
        }else if(node.val==target) {
            return node.leftCount;
        }else {
            return countSmaller(node.left, target);
        }
    }

    private int countBigger(TreeNode1 node, long target) {
        if(node==null) {
            return 0;
        }
        if(node.val>target) {
            return node.count+node.rightCount+countBigger(node.left, target);
        }else if(node.val==target) {
            return node.rightCount;
        }else {
            return countBigger(node.right, target);
        }
    }

}

class TreeNode1 {

    long val;
    int count;
    int leftCount;
    int rightCount;
    TreeNode1 left;
    TreeNode1 right;

    TreeNode1(long val, int count, int leftCount, int rightCount) {
        this.val = val;
        this.count = count;
        this.leftCount = leftCount;
        this.rightCount = rightCount;
    }

}