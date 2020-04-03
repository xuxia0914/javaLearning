package cn.leetcode.xux.midium;

/**
 * 1201. 丑数 III
 * 请你帮忙设计一个程序，用来找出第 n 个丑数。
 * 丑数是可以被 a 或 b 或 c 整除的 正整数。
 *
 * 示例 1：
 * 输入：n = 3, a = 2, b = 3, c = 5
 * 输出：4
 * 解释：丑数序列为 2, 3, 4, 5, 6, 8, 9, 10... 其中第 3 个是 4。
 *
 * 示例 2：
 * 输入：n = 4, a = 2, b = 3, c = 4
 * 输出：6
 * 解释：丑数序列为 2, 3, 4, 6, 8, 9, 12... 其中第 4 个是 6。
 *
 * 示例 3：
 * 输入：n = 5, a = 2, b = 11, c = 13
 * 输出：10
 * 解释：丑数序列为 2, 4, 6, 8, 10, 11, 12, 13... 其中第 5 个是 10。
 *
 * 示例 4：
 * 输入：n = 1000000000, a = 2, b = 217983653, c = 336916467
 * 输出：1999999984
 *
 * 提示：
 * 1 <= n, a, b, c <= 10^9
 * 1 <= a * b * c <= 10^18
 * 本题结果在 [1, 2 * 10^9] 的范围内
 */
public class UglyNumberIII {

    public int nthUglyNumber(int n, int a, int b, int c) {
        if(n<1||a<1||b<1||c<1) {
            throw new IllegalArgumentException("illeagal input");
        }

        // 两两组合的最小公倍数
        long lcmAB = lcm(a, b);
        long lcmAC = lcm(a, c);
        long lcmBC = lcm(b, c);
        // 三个数的最小公倍数
        long lcmABC = lcm(lcmAB, c);

        // lcm之内的数字数目，即一个周期内的元素数
        long m = lcmABC/a+lcmABC/b+lcmABC/c-lcmABC/lcmAB-lcmABC/lcmAC-lcmABC/lcmBC+1;

        long cycleCnt = n/m;
        long r = n%m;
        long result = cycleCnt*lcmABC;
        if(r>0) {
            long left = 1;
            long right = lcmABC;
            while(left<right) {
                long mid = left+(right-left)/2;
                long idx = mid/a+mid/b+mid/c-mid/lcmAB-mid/lcmAC-mid/lcmBC+mid/lcmABC;
                if(idx>=r) {
                    right = mid;
                }else {
                    left = mid+1;
                }
            }
            result += left;
        }
        return (int)result;
    }

    // 最小公倍数
    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    // 最大公因数
    private long gcd(long x, long y) {
        if (x == 0) {
            return y;
        }
        return gcd(y % x, x);
    }

}
