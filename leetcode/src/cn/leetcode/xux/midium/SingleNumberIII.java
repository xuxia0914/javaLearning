package cn.leetcode.xux.midium;

import java.util.HashSet;
import java.util.Set;

/**
 * 260. 只出现一次的数字 III
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
 *
 * 示例 :
 * 输入: [1,2,1,3,2,5]
 * 输出: [3,5]
 *
 * 注意：
 * 结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
 * 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 */
public class SingleNumberIII {

    public int[] singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i : nums) {
            if(!set.add(i)) {
                set.remove(i);
            }
        }
        int[] res = new int[set.size()];
        int idx = 0;
        for(int i : set) {
            res[idx++] = i;
        }
        return res;
    }

    /**
     * 设结果为a, b先对所有数异或得到c
     * 再执行c &= -c (得到c最右的为1的位，a和b在该位上的数必然不一样，一个为1，一个为0)
     * 再遍历数组区分取异或，可以分别求出a,b
     * @param nums
     * @return
     */
    public int[] singleNumber1(int[] nums) {
        int c = nums[0];
        for(int i=1;i<nums.length;i++) {
            c ^= nums[i];
        }
        c &= -c;
        int a = 0;
        int b = 0;
        for(int i : nums) {
            if((i&c)==0) {
                a ^= i;
            }else {
                b ^= i;
            }
        }
        return new int[]{a, b};
    }

    public static void main(String[] args) {
        SingleNumberIII sn = new SingleNumberIII();
        System.out.println(sn.singleNumber1(new int[]{1,2,1,3,2,5}));
    }

}
