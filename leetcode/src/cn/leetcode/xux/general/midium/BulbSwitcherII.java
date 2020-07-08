package cn.leetcode.xux.general.midium;

import java.util.HashSet;
import java.util.Set;

/**
 * 672. 灯泡开关 Ⅱ
 * 现有一个房间，墙上挂有 n 只已经打开的灯泡和 4 个按钮。
 * 在进行了 m 次未知操作后，你需要返回这 n 只灯泡可能有多少种不同的状态。
 * 假设这 n 只灯泡被编号为 [1, 2, 3 ..., n]，这 4 个按钮的功能如下：
 * 将所有灯泡的状态反转（即开变为关，关变为开）
 * 将编号为偶数的灯泡的状态反转
 * 将编号为奇数的灯泡的状态反转
 * 将编号为 3k+1 的灯泡的状态反转（k = 0, 1, 2, ...)
 *
 * 示例 1:
 * 输入: n = 1, m = 1.
 * 输出: 2
 * 说明: 状态为: [开], [关]
 *
 * 示例 2:
 * 输入: n = 2, m = 1.
 * 输出: 3
 * 说明: 状态为: [开, 关], [关, 开], [关, 关]
 *
 * 示例 3:
 * 输入: n = 3, m = 1.
 * 输出: 4
 * 说明: 状态为: [关, 开, 关], [开, 关, 开], [关, 关, 关], [关, 开, 开].
 * 注意： n 和 m 都属于 [0, 1000].
 */
public class BulbSwitcherII {

    public static void main(String[] args) {
        System.out.println(new BulbSwitcherII().flipLights(1,1));
    }

    public int flipLights(int n, int m) {
        if(n==0||m==0) {
            return 1;
        }
        //每次反转第x个灯泡，第x+6个灯泡也必被反转，所以前6个灯泡可以决定一个状态
        n = Math.min(n, 6);
        //每种操作执行两次相当于没有执行，所以每种操作执行的次数可以简化为0或者1
        m %= 16;
        int status = (int)Math.pow(2, n) - 1;
        //灯泡数量小于6时，状态取二进制数的前n位
        status <<= (6-n);
        Set<Integer> set = new HashSet<>();
        set.add(status);
        Set<Integer> tmp = new HashSet<>();
        for(int i=0;i<m;i++) {
            tmp.clear();
            for(int x : set) {
                tmp.add(x^0b111111&status);
                tmp.add(x^0b010101&status);
                tmp.add(x^0b101010&status);
                tmp.add(x^0b100100&status);
            }
            set = new HashSet<>(tmp);
        }
        return set.size();
    }

    public int flipLights1(int n, int m) {
        n = Math.min(n, 3);
        if (m == 0) return 1;
        if (m == 1) return n == 1 ? 2 : n == 2 ? 3 : 4;
        if (m == 2) return n == 1 ? 2 : n == 2 ? 4 : 7;
        return n == 1 ? 2 : n == 2 ? 4 : 8;
    }

}
