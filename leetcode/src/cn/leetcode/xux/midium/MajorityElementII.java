package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 229. 求众数 II
 * 给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 *
 * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。
 *
 * 示例 1:
 * 输入: [3,2,3]
 * 输出: [3]
 *
 * 示例 2:
 * 输入: [1,1,1,3,3,2,2,2]
 * 输出: [1,2]
 */
public class MajorityElementII {

    /**
     * Boyer-Moore 投票算法
     * 多数投票升级版：
     * 超过n/3的数最多只能有两个。先选出两个候选人A,B。 遍历数组，分三种情况：
     * 1.如果投A（当前元素等于A），则A的票数++;
     * 2.如果投B（当前元素等于B），B的票数++；
     * 3.如果A,B都不投（即当前与A，B都不相等）,那么检查此时A或B的票数是否减为0：
     *  3.1 如果为0,则当前元素成为新的候选人；
     *  3.2 如果A,B两个人的票数都不为0，那么A,B两个候选人的票数均减一；
     * 4.遍历结束后选出了两个候选人，但是这两个候选人是否满足>n/3，还需要再遍历一遍数组，找出两个候选人的具体票数。
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if(nums==null||nums.length==0) {
            return res;
        }
        int curr1 = nums[0];
        int curr2 = nums[0];
        int cnt1 = 0;
        int cnt2 = 0;
        for(int num : nums) {
            if(num==curr1) {
                cnt1++;
                continue;
            }
            if(num==curr2) {
                cnt2++;
                continue;
            }
            //此时当前值和AB都不等，检查是否有票数减为0的情况，如果为0，则更新候选人
            if(cnt1==0) {
                curr1 = num;
                cnt1 = 1;
                continue;
            }
            if(cnt2==0) {
                curr2 = num;
                cnt2 = 1;
                continue;
            }
            //若此时两个候选人的票数都不为0，且当前元素不投AB，那么A,B对应的票数都要--;
            cnt1--;
            cnt2--;
        }
        cnt1 = 0;
        cnt2 = 0;
        for(int num : nums) {
            if(num==curr1) {
                cnt1++;
            }else if(num==curr2) {
                cnt2++;
            }
        }

        //上一轮遍历找出了两个候选人，但是这两个候选人是否均满足票数大于N/3仍然没法确定，需要重新遍历，确定票数
        int target = nums.length/3;
        if(cnt1>target) {
            res.add(curr1);
        }
        if(cnt2>target) {
            res.add(curr2);
        }
        return res;
    }

    public List<Integer> majorityElement1(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if(nums==null||nums.length==0) {
            return res;
        }
        int target = nums.length/3;
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue()>target) {
                res.add(entry.getKey());
            }
        }
        return res;
    }

}
