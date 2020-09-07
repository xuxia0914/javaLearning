package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 1060. 有序数组中的缺失元素
 * 给出一个有序数组 A，数组中的每个数字都是 独一无二的，找出从数组最左边开始的第 K 个缺失数字。
 *
 * 示例 1：
 * 输入：A = [4,7,9,10], K = 1
 * 输出：5
 * 解释：第一个缺失数字为 5 。
 *
 * 示例 2：
 * 输入：A = [4,7,9,10], K = 3
 * 输出：8
 * 解释：缺失数字有 [5,6,8,...]，因此第三个缺失数字为 8 。
 *
 * 示例 3：
 * 输入：A = [1,2,4], K = 3
 * 输出：6
 * 解释：缺失数字有 [3,5,6,7,...]，因此第三个缺失数字为 6 。
 *
 * 提示：
 * 1 <= A.length <= 50000
 * 1 <= A[i] <= 1e7
 * 1 <= K <= 1e8
 */
public class MissingElementInSortedArray {

    public static void main(String[] args) {
        MissingElementInSortedArray mis = new MissingElementInSortedArray();
        System.out.println(mis.missingElement(new int[]{4,7,9,10}, 1));
        System.out.println(mis.missingElement(new int[]{4,7,9,10}, 3));
        System.out.println(mis.missingElement(new int[]{1,2,4}, 3));
    }

    public int missingElement(int[] nums, int k) {
        if(nums==null) {
            return 0;
        }
        int left = 0;
        int right = nums.length-1;
        while(left<right) {
            int mid = (left+right+1)/2;
            int cntMissing = nums[mid]-nums[left]-mid;
            if(cntMissing>=k) {
                right = mid-1;
            }else {
                left = mid;
            }
        }
        return k+nums[0]+left;
    }

}
