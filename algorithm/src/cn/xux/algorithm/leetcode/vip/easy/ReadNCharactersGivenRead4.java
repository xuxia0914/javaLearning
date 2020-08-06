package cn.xux.algorithm.leetcode.vip.easy;

/**
 * 157、用 Read4 读取 N 个字符
 *
 * 给你一个文件，并且该文件只能通过给定的 read4 方法来读取，
 * 请实现一个方法使其能够读取 n 个字符。返回值为实际读取的字符个数。
 *
 * 示例：
 *
 * 输入： file = "abc", n = 4
 * 输出： 3
 * 解释： 当执行你的 rand 方法后，buf 需要包含 "abc"。 文件一共 3 个字符，因此返回 3。
 * 注意 "abc" 是文件的内容，不是 buf 的内容，buf 是你需要写入结果的目标缓存区。
 */
public class ReadNCharactersGivenRead4 {

    public int read(char[] buf, int n) {
        int pos = 0;
        char[] buf4 = new char[4];
        int read = read4(buf4);
        while (pos < n && read > 0) {
            for(int i=0; i<read; i++) buf[pos++] = buf4[i];
            read = read4(buf4);
        }
        if (pos > n) pos = n;
        return pos;
    }

    //The read4 API is defined in the parent class Reader4.
    int read4(char[] buf) {
        return 0;
    }

}
