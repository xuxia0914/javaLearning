package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;

/**
 * 1625. 执行操作后字典序最小的字符串
 * 给你一个字符串 s 以及两个整数 a 和 b 。
 * 其中，字符串 s 的长度为偶数，且仅由数字 0 到 9 组成。
 * 你可以在 s 上按任意顺序多次执行下面两个操作之一：
 * 累加：将  a 加到 s 中所有下标为奇数的元素上（下标从 0 开始）。
 * 数字一旦超过 9 就会变成 0，如此循环往复。
 * 例如，s = "3456" 且 a = 5，则执行此操作后 s 变成 "3951"。
 * 轮转：将 s 向右轮转 b 位。例如，s = "3456" 且 b = 1，则执行此操作后 s 变成 "6345"。
 * 请你返回在 s 上执行上述操作任意次后可以得到的 字典序最小 的字符串。
 * 如果两个字符串长度相同，那么字符串 a 字典序比字符串 b 小可以这样定义：
 * 在 a 和 b 出现不同的第一个位置上，字符串 a 中的字符出现在字母表中的时间早于 b 中的对应字符。
 * 例如，"0158” 字典序比 "0190" 小，因为不同的第一个位置是在第三个字符，显然 '5' 出现在 '9' 之前。
 *
 * 示例 1：
 * 输入：s = "5525", a = 9, b = 2
 * 输出："2050"
 * 解释：执行操作如下：
 * 初态："5525"
 * 轮转："2555"
 * 累加："2454"
 * 累加："2353"
 * 轮转："5323"
 * 累加："5222"
 * 累加："5121"
 * 轮转："2151"
 * 累加："2050"​​​​​​​​​​​​
 * 无法获得字典序小于 "2050" 的字符串。
 *
 * 示例 2：
 * 输入：s = "74", a = 5, b = 1
 * 输出："24"
 * 解释：执行操作如下：
 * 初态："74"
 * 轮转："47"
 * 累加："42"
 * 轮转："24"​​​​​​​​​​​​
 * 无法获得字典序小于 "24" 的字符串。
 *
 * 示例 3：
 * 输入：s = "0011", a = 4, b = 2
 * 输出："0011"
 * 解释：无法获得字典序小于 "0011" 的字符串。
 *
 * 示例 4：
 * 输入：s = "43987654", a = 7, b = 3
 * 输出："00553311"
 *
 * 提示：
 * 2 <= s.length <= 100
 * s.length 是偶数
 * s 仅由数字 0 到 9 组成
 * 1 <= a <= 9
 * 1 <= b <= s.length - 1
 */
public class LexicographicallySmallestStringAfterApplyingOperations {

    public static void main(String[] args) {
        LexicographicallySmallestStringAfterApplyingOperations lssaa =
                new LexicographicallySmallestStringAfterApplyingOperations();
        System.out.println(lssaa.findLexSmallestString("5525", 9, 2));
        System.out.println(lssaa.findLexSmallestString("74", 5, 1));
        System.out.println(lssaa.findLexSmallestString("0011", 4, 2));
        System.out.println(lssaa.findLexSmallestString("43987654", 7, 3));
    }

    public String findLexSmallestString(String s, int a, int b) {
        int len = s.length();
        // 计算有效增加步幅、有效轮转步幅
        int step1 = gcd(10, a), step2 = gcd(len, b);
        // 定义原始数组与结果数组
        char[] origin = s.toCharArray();
        char[] res = Arrays.copyOf(origin, len);
        // 循环执行所有有效轮转
        for(int i = 0; i < len; i += step2){
            // 对每个轮转位置Copy数组
            move(origin, step2);
            char[] chars = Arrays.copyOf(origin, len);
            // 对所有奇数位，分别在原基础上增加各个有效增幅
            for(int j = 0; j < 10; j += step1){
                for(int k = 1; k < len; k += 2){
                    chars[k] = (char)((origin[k] - '0' + j) % 10 + '0');
                }
                // 如果实际轮转步幅为奇数，则必有其他轮转状态使得可以在偶数位上增加各个有效增幅
                if(b % 2 == 0){
                    if(compare(chars, res) < 0) res = Arrays.copyOf(chars, len);
                }else{
                    // 此处直接针对所有偶数位，分别增加所有可能增幅并比较
                    for(int t = 0; t < 10; t += step1){
                        for(int k = 0; k < len; k += 2){
                            chars[k] = (char)((origin[k] - '0' + t) % 10 + '0');
                        }
                        if(compare(chars, res) < 0) res = Arrays.copyOf(chars, len);
                    }
                }
            }
        }
        return new String(res);
    }
    public void move(char[] arr, int step){
        int len = arr.length;
        char[] tmp = new char[step];
        for(int i = 0; i < step; i ++) tmp[i] = arr[len - step + i];
        for(int i = len - 1; i >= step; i --) arr[i] = arr[i - step];
        for(int i = 0; i < step; i ++) arr[i] = tmp[i];
    }
    public int compare(char[] chars1, char[] chars2){
        for(int i = 0; i < chars1.length; i ++){
            if(chars1[i] != chars2[i]){
                return chars1[i] - chars2[i];
            }
        }
        return 0;
    }
    public int gcd(int a, int b){
        return b == 0? a: gcd(b, a % b);
    }

}
