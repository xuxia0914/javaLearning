package cn.xux.algorithm.leetcode.general.hard;

/**
 * 41. 缺失的第一个正数
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 *
 * 示例 1:
 * 输入: [1,2,0]
 * 输出: 3
 *
 * 示例 2:
 * 输入: [3,4,-1,1]
 * 输出: 2
 *
 * 示例 3:
 * 输入: [7,8,9,11,12]
 * 输出: 1
 *
 * 说明:
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        if(nums==null||nums.length==0) {
            return 1;
        }
        int len = nums.length;
        int i = 0;
        /**
         * 把值nums[i](1<=nums[i]<=len)与索引为nums[i]-1的值替换，让每个符合条件(1<=nums[i]<=len且nums[i]!=i+1且nums[nums[i]-1]!=nums[i])的值都归位到数组的索引i-1上(已经归位的就不再替换，否则会死循环)
         * 比如：[3, 4, -1, 1]处理后变为[1, -1, 3, 4]
         * 最后再遍历一次数组，找出第一个不符合nums[i]==i+1的数
         */
        while(i<len) {
            if(nums[i]<=0||nums[i]>len||nums[i]==i+1||nums[i]==nums[nums[i]-1]) {
                i++;
            }else {
                int tmp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = tmp;
            }
        }
        for(int j=0;j<len;j++) {
            if(nums[j]!=j+1) {
                return j+1;
            }
        }
        return len+1;
    }

    public static void main(String[] args) {
        FirstMissingPositive fmp = new FirstMissingPositive();
        System.out.println(fmp.firstMissingPositive(new int[]{1,2,0}));
        System.out.println(fmp.firstMissingPositive(new int[]{3,4,-1,1}));
        System.out.println(fmp.firstMissingPositive(new int[]{7,8,9,11,12}));
        System.out.println(fmp.firstMissingPositive(new int[]{1,1}));
    }

}
