package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1806. 还原排列的最少操作步数
 * 给你一个偶数 n​​​​​​ ，已知存在一个长度为 n 的排列 perm ，
 * 其中 perm[i] == i​（下标 从 0 开始 计数）。
 * 一步操作中，你将创建一个新数组 arr ，对于每个 i ：
 * 如果 i % 2 == 0 ，那么 arr[i] = perm[i / 2]
 * 如果 i % 2 == 1 ，那么 arr[i] = perm[n / 2 + (i - 1) / 2]
 * 然后将 arr​​ 赋值​​给 perm 。
 * 要想使 perm 回到排列初始值，至少需要执行多少步操作？返回最小的 非零 操作步数。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：1
 * 解释：最初，perm = [0,1]
 * 第 1 步操作后，perm = [0,1]
 * 所以，仅需执行 1 步操作
 *
 * 示例 2：
 * 输入：n = 4
 * 输出：2
 * 解释：最初，perm = [0,1,2,3]
 * 第 1 步操作后，perm = [0,2,1,3]
 * 第 2 步操作后，perm = [0,1,2,3]
 * 所以，仅需执行 2 步操作
 *
 * 示例 3：
 * 输入：n = 6
 * 输出：4
 *
 * 提示：
 * 2 <= n <= 1000
 * n​​​​​​ 是一个偶数
 */
public class MinimumNumberOfOperationsToReinitializeAPermutation {

    /**
     * 对于原排列中第 ii 个元素，设 f(i)f(i) 为进行一次操作后，该元素的新的下标。根据题意：
     *
     * 当 i < n/2i<n/2 时，f(i)=2if(i)=2i（第一条规则）
     * 当 i \ge n/2i≥n/2 时，f(i)=2i-(n-1)f(i)=2i−(n−1)（第二条规则）
     * 原排列中的第 00 和 n-1n−1 个元素的下标是不会变化的，我们无需进行考虑。
     * 对于其余元素 i \in [1, n-1)i∈[1,n−1)，都有 f(i) \equiv 2i \bmod (n-1)f(i)≡2imod(n−1)。
     *
     * 下面，记 f^k(i)f
     * k
     *  (i) 为原排列中第 ii 个元素操作 kk 次后的下标，即 f^2(i) = f(f(i)), f^3(i) = f(f(f(i)))f
     * 2
     *  (i)=f(f(i)),f
     * 3
     *  (i)=f(f(f(i))) 等等，那么我们有
     *
     * f^k(i) \equiv 2^ki \bmod (n-1)
     * f
     * k
     *  (i)≡2
     * k
     *  imod(n−1)
     *
     * 这意味着：为了让排列还原到初始值，必须有 f^k(i) \equiv 2^ki \equiv i \bmod (n-1)f
     * k
     *  (i)≡2
     * k
     *  i≡imod(n−1)。
     *
     * 当 i=1i=1 时，我们有
     *
     * f^k(1) \equiv 2^k \equiv 1 \bmod (n-1)
     * f
     * k
     *  (1)≡2
     * k
     *  ≡1mod(n−1)
     *
     * 如果存在 kk 满足上式，那么将上式两侧同乘 ii，得到 f^k(i) \equiv i \bmod (n-1)f
     * k
     *  (i)≡imod(n−1) 即对于 i \in [1, n-1)i∈[1,n−1) 恒成立。因此，原题等价于寻找最小的 kk，使得 2^k \equiv 1 \bmod (n-1)2
     * k
     *  ≡1mod(n−1)。
     *
     *  由于 n-1n−1 是奇数，22 和 n-1n−1 互质，那么根据 欧拉定理
     *
     * 2^{\varphi(n-1)} \equiv 1 \bmod (n-1)
     * 2
     * φ(n−1)
     *  ≡1mod(n−1)
     *
     * 即 k=\varphi(n-1)k=φ(n−1) 一定是一个解，其中 \varphiφ 为 欧拉函数。因此，最小的 kk 一定小于等于 \varphi(n-1)φ(n−1)。而后者是 O(n-1) = O(n)O(n−1)=O(n) 的，因此总的时间复杂度为 O(n)O(n)。
     *
     * 注记 1：题面中给出的是新数组中下标为 ii 的元素来源自原数组中的哪个元素，即为 ff 的反函数。为了从反函数中求解出 ff，可以利用换元法。
     * 注记 2：为了推导出 i \in [1, n-1)i∈[1,n−1)，都有 f(i) = 2i \bmod (n-1)f(i)=2imod(n−1)，需要对两种情况做分类讨论。
     * 注记 3：本思路来自网站 Project Euler 的题目 Riffle Shuffles。
     *
     * 作者：Arsenal-591
     * 链接：https://leetcode-cn.com/problems/minimum-number-of-operations-to-reinitialize-a-permutation/solution/shu-xue-on-suan-fa-by-arsenal-591-xatz/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int reinitializePermutation(int n) {
        if(n==2) {
            return 1;
        }
        int ans = 1;
        int pow = 2;
        while(pow!=1) {
            ans++;
            pow = pow*2%(n-1);
        }
        return ans;
    }

    // 暴力模拟
    public int reinitializePermutation1(int n) {
        if (n%2!=0) {
            return 0;
        }
        int[] perm = new int[n];
        for (int i = 0; i < perm.length; i++) {
            perm[i] = i;
        }
        int[] arr = new int[n];
        int count = 0;
        while (true) {
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    arr[i] = perm[i / 2];
                } else {
                    arr[i] = perm[n / 2 + (i - 1) / 2];
                }
            }
            count++;
            if (check(arr)) {
                return count;
            }
            perm = arr.clone();
        }
    }

    private boolean check(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i) {
                return false;
            }
        }
        return true;
    }

}
