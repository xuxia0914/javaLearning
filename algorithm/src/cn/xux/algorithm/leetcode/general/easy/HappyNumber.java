package cn.xux.algorithm.leetcode.general.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 202. 快乐数
 * 编写一个算法来判断一个数是不是“快乐数”。
 * 一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，
 * 也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。
 *
 * 示例:
 * 输入: 19
 * 输出: true
 * 解释:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 */
public class HappyNumber {

    public boolean isHappy(int n) {
        if(n<1) {
            return false;
        }
        int slow = helper(n);
        int fast = helper(helper(n));
        while(slow!=fast) {
            slow = helper(n);
            fast = helper(helper(n));
        }
        return slow==1;
    }

    public boolean isHappy1(int n) {
        if(n<1) {
            return false;
        }
        Set<Integer> nums = new HashSet<>();
        int tmp = n;
        while(true) {
            tmp = helper(tmp);
            if(tmp==1) {
                return true;
            }else if(!nums.add(tmp)){
                return false;
            }
        }
    }

    public int helper(int n) {
        int res=0, i;
        while(n>0) {
            i = n%10;
            n /= 10;
            res += i*i;
        }
        return res;
    }

}
