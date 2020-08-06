package cn.xux.algorithm.leetcode.general.midium;

/**
 * 179. 最大数
 * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
 *
 * 示例 1:
 * 输入: [10,2]
 * 输出: 210
 *
 * 示例 2:
 * 输入: [3,30,34,5,9]
 * 输出: 9534330
 * 说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 */
public class LargestNumber {

    public String largestNumber(int[] nums) {
        if(nums==null||nums.length==0) {
            return "";
        }
        sort(nums, 0, nums.length-1);
        StringBuilder sb = new StringBuilder();
        for(int num : nums) {
            sb.append(num);
        }
        String s = sb.toString();
        int i;
        for(i=0;i<s.length();i++) {
            if(s.charAt(i)!='0') {
                break;
            }
        }
        return i==s.length()?"0":s.substring(i);
    }

    //自定义二分排序
    public void sort(int[] nums, int start, int end) {
        if(start>=end) {
            return;
        }
        int left = start;
        int right = end;
        int key = nums[start];
        while(left<right) {
            while(left<right&&compare(key, nums[right])) {
                right--;
            }
            while(left<right&&compare(nums[left], key)) {
                left++;
            }
            if(left<right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
            }
        }
        nums[start] = nums[right];
        nums[right] = key;
        sort(nums, start, right-1);
        sort(nums, right+1, end);
    }

    //自定义比较：判断a是否应该排在b前面
    public boolean compare(int a, int b) {
        String s1 = ""+a+b;
        String s2 = ""+b+a;
        for(int i=0;i<s1.length();i++) {
            if(s1.charAt(i)>s2.charAt(i)) {
                return true;
            }else if(s1.charAt(i)<s2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

}
