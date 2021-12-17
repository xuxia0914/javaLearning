package cn.xux.algorithm.lintcode;

import java.util.*;

/**
 * 1766. 构造队列 II
 * cat-only-icon
 * CAT 专属题目
 * 中文English
 * 存在一个数列的某种排列。现在已知数列中每个数的大小即 arr1，
 * 和每个数之前有多少个比它自身大的数即 arr2，
 * 要求恢复出原来的排列。保证数列中的元素两两不等。
 *
 * 样例
 * 样例 1:
 *
 * 输入: arr1=[1,2,3,4,5], arr2=[4,2,0,0,0]
 * 输出: [3,4,2,5,1]
 * 解释:
 * 令 arr = []
 * 对于元素 1：在前面有 4 个元素比元素 1 大，并且 n = 5，所以arr = [ , , , , 1]
 * 对于元素 2：在前面有 2 个元素比元素 2 大，并且元素 1 位于 arr 最后，所以元素 2 位于 arr 第 3 个位置，所以 arr = [ , , 2, , 1]
 * 对于元素 3：在前面有 0 个元素比元素 3 大，并且元素 1、2 位于 arr 第 5 、3个位置，那么元素 3 位于 arr 第 1 个位置，所以 arr = [3, , 2, , 1]
 * 对于元素 4：在前面有 0 个元素比元素 4 大，若元素 4 在 arr 的第 4 个位置，那么前面就有 1 个元素比元素 4 大，所以元素 4 位于 arr 第 2 个位置，
 * 元素 5 位于arr 第 4 个位置，所以 arr = [3, 4, 2, 5, 1]
 * 返回 [3, 4, 2, 5, 1]
 * 样例 2:
 *
 * 输入: arr1=[1,3,7,6], arr2=[0,0,0,0]
 * 输出: [1,3,6,7]
 * 解释:
 * 因为 arr1 中每个元素对应的 arr2 都为 0，也就意味着每个元素前面都没有元素比本身大
 * 所以对于 arr1 中的元素，在原始排列中都是递增的，即为 arr = [1, 3, 6, 7]
 * 所以返回 [1, 3, 6, 7]
 *
 * 注意事项
 * 1 ≤ n ≤ 10^5
 */
public class Lintcode1766 {

    public static void main(String[] args) {
        //[1,2,3,4,5]
        //[3,0,0,0,0]
        System.out.println(Arrays.toString(new Lintcode1766().getQueue(new int[]{1,2,3,4,5}, new int[]{3,0,0,0,0})));
        System.out.println(Arrays.toString(new Lintcode1766().getQueue(new int[]{1,2,3,4,5}, new int[]{4,2,0,0,0})));
        System.out.println(Arrays.toString(new Lintcode1766().getQueue(new int[]{1,3,7,6}, new int[]{0,0,0,0})));
    }

    /**
     * 按元素从小打到排列
     * 树状数组存储位置是否已确定元素（确定了则置0）
     * 按从小到大确定当前元素的位置（找出第一个位置，该位置对应的树状数组的前缀和等于当前元素左边的更大元素的个数+1）
     * @param arr1: The size
     * @param arr2: How many numbers bigger than itself
     * @return: The correct array
     * o(n*(logn)^2)
     */
    public int[] getQueue(int[] arr1, int[] arr2) {
        // Write your code here
        if (arr1 == null || arr1.length == 0) {
            return new int[0];
        }
        int n = arr1.length;
        int[][] pairs = new int[n][2];
        for(int i=0;i<n;i++) {
            pairs[i][0] = arr1[i];
            pairs[i][1] = arr2[i];
        }
        Arrays.sort(pairs, Comparator.comparingInt(o->o[0]));
        // 用树状数组记录每个元素是否已经被确定
        BIT bit = new BIT(n+1);
        for(int i=1;i<=n;i++) {
            bit.add(i, 1);
        }
        int[] ans = new int[n];
        for(int i=0;i<n;i++) {
            int tar = pairs[i][1]+1;
            int left = 0;
            int right = n-1;
            while(left<right) {
                int mid = (left+right)>>1;
                if(bit.query(mid+1)>=tar) {
                    right = mid;
                }else {
                    left = mid+1;
                }
            }
            ans[left] = pairs[i][0];
            bit.add(left+1, -1);
        }
        return ans;
    }

    class BIT {

        int[] tree;

        int n;

        BIT(int n) {
            this.n = n;
            tree = new int[n];
        }

        private int lowBit(int x) {
            return x&(-x);
        }

        public void add(int x, int y) {
            while(x<n) {
                tree[x] += y;
                x += lowBit(x);
            }
        }

        public int query(int x) {
            int ans = 0;
            while(x>0) {
                ans += tree[x];
                x -= lowBit(x);
            }
            return ans;
        }

    }

    /**
     * 将元素按前面更大元素的数量分组后再升序(小顶堆的数组)
     * queues[i]表示前面还剩下的更大元素的数量为i的所有元素
     * 每次取queues[0]的第一个元素，并更新所有后面的更小元素(剩余的更大元素-1.即元素当前在数组的索引-1)
     * @param arr1: The size
     * @param arr2: How many numbers bigger than itself
     * @return: The correct array
     */
    //MLE
    public int[] getQueue1(int[] arr1, int[] arr2) {
        // Write your code here
        if(arr1==null||arr1.length==0) {
            return new int[0];
        }
        int n = arr1.length;
        PriorityQueue<Integer>[] queues = new PriorityQueue[n];
        for(int i=0;i<n;i++) {
            queues[i] = new PriorityQueue<>();
        }
        for(int i=0;i<n;i++) {
            queues[arr2[i]].add(arr1[i]);
        }
        int[] ans = new int[n];
        for(int i=0;i<n;i++) {
            int curr = queues[0].poll();
            ans[i] = curr;
            for(int j=1;j<n;j++) {
                PriorityQueue<Integer> queue = queues[j];
                while(!queue.isEmpty()&&queue.peek()<curr) {
                    queues[j-1].add(queue.poll());
                }
            }
        }
        return ans;
    }

}
