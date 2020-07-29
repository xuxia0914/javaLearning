package cn.leetcode.xux.general.midium;

/**
 * 307. 区域和检索 - 数组可修改
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 * update(i, val) 函数可以通过将下标为 i 的数值更新为 val，从而对数列进行修改。
 *
 * 示例:
 * Given nums = [1, 3, 5]
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 *
 * 说明:
 * 数组仅可以在 update 函数下进行修改。
 * 你可以假设 update 函数与 sumRange 函数的调用次数是均匀分布的。
 */
public class RangeSumQueryMutable {

    public static void main(String[] args) {
        NumArray na = new NumArray(new int[]{1, 3, 5});
        System.out.println(na.sumRange(0, 2));
        System.out.println(na.sumRange(0, 1));
        System.out.println(na.sumRange(1, 2));
        na.update(0, 2);
        System.out.println(na.sumRange(0, 2));
        System.out.println(na.sumRange(0, 1));
        System.out.println(na.sumRange(1, 2));
    }

}

/**
 * 线段树
 * 线段树是一种非常灵活的数据结构，它可以用于解决多种范围查询问题，
 * 比如在对数时间内从数组中找到最小值、最大值、总和、最大公约数、最小公倍数等
 * O(logn), O(logn)
 */
class NumArray {

    int[] tree;
    int n;

    public NumArray(int[] nums) {
        n = nums.length;
        tree = new int[n*2];
        for(int i=n,j=0;i<n*2;i++,j++) {
            tree[i] = nums[j];
        }
        for(int i=n-1;i>0;i--) {
            tree[i] = tree[i*2]+tree[i*2+1];
        }
    }

    public void update(int i, int val) {
        int pos = i+n;
        tree[pos] = val;
        int left, right;
        while(pos>1) {
            left = pos;
            right = pos;
            if(left%2==1) {
                left -= 1;
            }
            if(right%2==0) {
                right += 1;
            }
            tree[pos/2] = tree[left] + tree[right];
            pos /= 2;
        }
    }

    public int sumRange(int i, int j) {
        int left = i+n;
        int right = j+n;
        int sum = 0;
        while(left<=right) {
            if(left%2==1) {
                sum += tree[left++];
            }
            if(right%2==0) {
                sum += tree[right--];
            }
            left /= 2;
            right /= 2;
        }
        return sum;
    }

}

/**
 * 常规解法
 * O(n), O(1)
 */
class NumArray1 {

    int[] nums;

    public NumArray1(int[] nums) {
        this.nums = nums;
    }

    public void update(int i, int val) {
        this.nums[i] = val;
    }

    public int sumRange(int i, int j) {
        int sum = 0;
        for(int k=i;k<=j;k++) {
            sum += this.nums[k];
        }
        return sum;
    }

}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */