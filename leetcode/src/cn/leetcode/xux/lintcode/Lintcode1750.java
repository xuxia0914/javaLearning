package cn.leetcode.xux.lintcode;

/**
 * 1750. 区间加和求值
 * cat-only-icon
 * CAT 专属题目
 * 中文English
 * 已知一个数列，你需要进行下面两种操作：
 * 1.将一个区间每一个数加上x
 * 2.求出某一个数的值
 * 输入：
 * 原数组为A。为了方便，A[0]为0.实际数列从A[1]开始。
 * 操作通过4元组给出。
 * 对于每个4元组（a,b,c,d）：
 * 如果a=0 要求A[b]-A[c]区间的值都增加d(修改)。
 * 如果a=1 要求得到A[b]的值。其中c,d不起作用(查询)。
 *
 * 输出：
 * 为了减少输出数据量。将操作2(询问)的所有值异或(^) 后返回。
 *
 * 样例
 * 样例 1:
 *
 * 输入：[0,1,2,3,4],[[1,1,0,0],[0,1,2,1],[1,2,0,0]]
 * 输出：2
 * 解释：
 * 第一个操作返回A [1] = 1
 * 第二个操作改变A为 [0,2,3,3,4]
 * 第三个操作返回A [1] = 3
 * 所以 1 ^ 3 = 2
 * 样例 2:
 *
 * 输入：[0,1],[[1,1,0,0]]
 * 输出：1
 * 解释：第一个操作返回A [1] = 1,答案为 1。
 * 注意事项
 * 数列长度<=10000
 * 操作数<=50000
 */
public class Lintcode1750 {

    /**
     * @param A:
     * @param operations:
     * @return: nothing
     */
    public long intervalsAddAndGetValue(int[] A, int[][] operations) {
        // Write your code here
        if(A==null||A.length<2||operations==null||operations.length==0) {
            return 0;

        }
        int n = A.length;
        int[] arr = new int[n];
        long ans = 0;
        for(int[] operation : operations) {
            if(operation[0]==0) {
                arr[operation[1]] += operation[3];
                if(operation[2]<n-1) {
                    arr[operation[2]+1] -= operation[3];
                }
            }else {
                long preSum = preSum(arr, operation[1]);
                ans ^= (preSum+A[operation[1]]);
            }
        }
        return ans;
    }

    private long preSum(int[] arr, int idx) {
        long ans = 0;
        for(int i=0;i<=idx;i++) {
            ans += arr[i];
        }
        return ans;
    }

}
