package cn.xux.algorithm.leetcode.general.midium;

/**
 * 307. 区域和检索 - 数组可修改
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 * update(i, val) 函数可以通过将下标为 i 的数值更新为 val，从而对数列进行修改。
 * <p>
 * 示例:
 * Given nums = [1, 3, 5]
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * <p>
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
 * 树状数组
 * 树状数组或二叉索引树（英语：Binary Indexed Tree），
 * 又以其发明者命名为Fenwick树，
 * 最早由Peter M. Fenwick于1994年以A New Data Structure for Cumulative Frequency Tables为题发表在SOFTWARE PRACTICE AND EXPERIENCE。
 * 其初衷是解决数据压缩里的累积频率（Cumulative Frequency）的计算问题，
 * 现多用于高效计算可变数组的前缀和， 区间和。
 * O(logn), O(logn)
 */
class NumArray {

    int[] tree;

    private int lowbit(int x) {
        return x & (-x);
    }

    public void add(int x, int val) {
        for (int i = x; i <= n; i += lowbit(i)) {
            tree[i] += val;
        }
    }

    public int query(int x) {
        int ans = 0;
        for (int i = x; i > 0; i -= lowbit(i)) {
            ans += tree[i];
        }
        return ans;
    }

    int[] nums;
    int n;

    public NumArray(int[] nums) {
        this.nums = nums;
        n = nums.length;
        tree = new int[n + 1];
        for (int i = 0; i < n; i++) {
            add(i + 1, nums[i]);
        }
    }

    public void update(int i, int val) {
        add(i + 1, val - nums[i]);
        nums[i] = val;
    }

    public int sumRange(int i, int j) {
        return query(j + 1) - query(i);
    }

}

/**
 * 线段树
 * 线段树是一种非常灵活的数据结构，它可以用于解决多种范围查询问题，
 * 比如在对数时间内从可变数组中找到最小值、最大值、总和、最大公约数、最小公倍数等
 * O(logn), O(logn)
 */
class NumArray1 {

    int[] tree;
    int n;

    public NumArray1(int[] nums) {
        n = nums.length;
        tree = new int[n * 2];
        for (int i = n, j = 0; i < n * 2; i++, j++) {
            tree[i] = nums[j];
        }
        for (int i = n - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    public void update(int i, int val) {
        int pos = i + n;
        tree[pos] = val;
        int left, right;
        while (pos > 1) {
            left = pos;
            right = pos;
            if (left % 2 == 1) {
                left -= 1;
            }
            if (right % 2 == 0) {
                right += 1;
            }
            tree[pos / 2] = tree[left] + tree[right];
            pos /= 2;
        }
    }

    public int sumRange(int i, int j) {
        int left = i + n;
        int right = j + n;
        int sum = 0;
        while (left <= right) {
            if (left % 2 == 1) {
                sum += tree[left++];
            }
            if (right % 2 == 0) {
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
class NumArray2 {

    int[] nums;

    public NumArray2(int[] nums) {
        this.nums = nums;
    }

    public void update(int i, int val) {
        this.nums[i] = val;
    }

    public int sumRange(int i, int j) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
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