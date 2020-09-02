package cn.xux.algorithm.leetcode.vip.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 751. IP 到 CIDR（贪心）
 * 给定一个起始 IP 地址 ip 和一个我们需要包含的 IP 的数量 n，
 * 返回用列表（最小可能的长度）表示的 CIDR块的范围。
 * CIDR 块是包含 IP 的字符串，后接斜杠和固定长度。
 * 例如：“123.45.67.89/20”。固定长度 “20” 表示在特定的范围中公共前缀位的长度。
 *
 * 示例 1：
 * 输入：ip = "255.0.0.7", n = 10
 * 输出：["255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"]
 * 解释：
 * 转换为二进制时，初始IP地址如下所示（为清晰起见添加了空格）：
 * 255.0.0.7 -> 11111111 00000000 00000000 00000111
 * 地址 "255.0.0.7/32" 表示与给定地址有相同的 32 位前缀的所有地址，
 * 在这里只有这一个地址。
 *
 * 地址 "255.0.0.8/29" 表示与给定地址有相同的 29 位前缀的所有地址：
 * 255.0.0.8 -> 11111111 00000000 00000000 00001000
 * 有相同的 29 位前缀的地址如下：
 * 11111111 00000000 00000000 00001000
 * 11111111 00000000 00000000 00001001
 * 11111111 00000000 00000000 00001010
 * 11111111 00000000 00000000 00001011
 * 11111111 00000000 00000000 00001100
 * 11111111 00000000 00000000 00001101
 * 11111111 00000000 00000000 00001110
 * 11111111 00000000 00000000 00001111
 *
 * 地址 "255.0.0.16/32" 表示与给定地址有相同的 32 位前缀的所有地址，
 * 这里只有 11111111 00000000 00000000 00010000。
 *
 * 总之，答案指定了从 255.0.0.7 开始的 10 个 IP 的范围。
 *
 * 有一些其他的表示方法，例如：
 * ["255.0.0.7/32","255.0.0.8/30", "255.0.0.12/30", "255.0.0.16/32"],
 * 但是我们的答案是最短可能的答案。
 *
 * 另外请注意以 "255.0.0.7/30" 开始的表示不正确，
 * 因为其包括了 255.0.0.4 = 11111111 00000000 00000000 00000100 这样的地址，
 * 超出了需要表示的范围。
 *
 * 注：
 * ip 是有效的 IPv4 地址。
 * 每一个隐含地址 ip + x (其中 x < n) 都是有效的 IPv4 地址。
 * n 为整数，范围为 [1, 1000]。
 */
public class IPToCIDR {

    public List<String> ipToCIDR(String ip, int n) {
        List<String> res = new ArrayList<>();
        String[] ss = ip.split("\\.");
        long x = Long.valueOf(ss[0])*256*256*256
                + Long.valueOf(ss[1])*256*256
                + Long.valueOf(ss[2])*256
                + Long.valueOf(ss[3]);
        long step;
        while(n>0) {
            step = x&-x;
            while(step>n) {
                step /= 2;
            }
            res.add(helper(x, step));
            x += step;
            n -= step;
        }
        return res;
    }

    public String helper(long x, long step) {
        StringBuilder sb = new StringBuilder();
        sb.append((x>>24)&255).append('.')
                .append((x>>16)&255).append('.')
                .append((x>>8)&255).append('.')
                .append(x&255)
                .append('/').append(32-(int)(Math.log(step)/Math.log(2)));
        return sb.toString();
    }

    public static void main(String[] args) {
        IPToCIDR itc = new IPToCIDR();
        System.out.println(itc.ipToCIDR("255.0.0.7", 10));
    }

}
