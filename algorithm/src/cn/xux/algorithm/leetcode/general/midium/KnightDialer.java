package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;

/**
 * 935. 骑士拨号器
 * 国际象棋中的骑士可以按下图所示进行移动：
 * 这一次，我们将 “骑士” 放在电话拨号盘的任意数字键（如上图所示）上，接下来，骑士将会跳 N-1 步。
 * 每一步必须是从一个数字键跳到另一个数字键。
 * 每当它落在一个键上（包括骑士的初始位置），都会拨出键所对应的数字，总共按下 N 位数字。
 * 你能用这种方式拨出多少个不同的号码？
 *  1 2 3
 *  4 5 6
 *  7 8 9
 *    0
 * 因为答案可能很大，所以输出答案模 10^9 + 7。
 *
 * 示例 1：
 * 输入：1
 * 输出：10
 *
 * 示例 2：
 * 输入：2
 * 输出：20
 *
 * 示例 3：
 * 输入：3
 * 输出：46
 *
 * 提示：
 * 1 <= N <= 5000
 */
public class KnightDialer {

    int[][] nexts = new int[][]{{4,6},{6,8},{7,9},{4,8},{0,3,9},{},{0,1,7},{2,6},{1,3},{2,4}};

    public int knightDialer(int N) {
        int[] cnts = new int[10];
        Arrays.fill(cnts, 1);
        int[] tmp;
        for(int i=1;i<N;i++) {
            tmp = cnts;
            cnts = new int[10];
            for(int j=0;j<10;j++) {
                for(int num : nexts[j]) {
                    cnts[num] = (cnts[num]+tmp[j])%1000000007;
                }
            }
        }
        int res = 0;
        for(int cnt : cnts) {
            res = (res+cnt)%1000000007;
        }
        return res;
    }

    public static void main(String[] args) {
        KnightDialer kd = new KnightDialer();
        System.out.println(kd.knightDialer(1));
        System.out.println(kd.knightDialer(2));
        System.out.println(kd.knightDialer(3));
        System.out.println(kd.knightDialer(54));
    }

}
