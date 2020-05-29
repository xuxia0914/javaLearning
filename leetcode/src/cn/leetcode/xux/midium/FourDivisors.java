package cn.leetcode.xux.midium;

/**
 * 1390. 四因数
 * 给你一个整数数组 nums，请你返回该数组中恰有四个因数的这些整数的各因数之和。
 * 如果数组中不存在满足题意的整数，则返回 0 。
 *
 * 示例：
 * 输入：nums = [21,4,7]
 * 输出：32
 * 解释：
 * 21 有 4 个因数：1, 3, 7, 21
 * 4 有 3 个因数：1, 2, 4
 * 7 有 2 个因数：1, 7
 * 答案仅为 21 的所有因数的和。
 *
 * 提示：
 * 1 <= nums.length <= 10^4
 * 1 <= nums[i] <= 10^5
 */
public class FourDivisors {

    public int sumFourDivisors(int[] nums) {
        int ans = 0;
        for(int num : nums) {
            int div = (int)Math.sqrt(num);
            if(div*div!=num) {
                int k = div;
                int sum = num+1;
                boolean flag1 = false;
                boolean flag2 = false;
                while(k>1) {
                    if(num%k==0) {
                        if(flag1) {
                            flag2 = true;
                            break;
                        }else {
                            sum += k+num/k;
                            flag1 = true;
                        }
                    }
                    k--;
                }
                if(!flag2&&flag1) {
                    ans += sum;
                }
            }
        }
        return ans;
    }

}
