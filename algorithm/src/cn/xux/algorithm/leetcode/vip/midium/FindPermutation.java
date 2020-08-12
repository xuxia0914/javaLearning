package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 484. 寻找排列
 * 现在给定一个只由字符 'D' 和 'I' 组成的 秘密签名。
 * 'D' 表示两个数字间的递减关系，'I' 表示两个数字间的递增关系。
 * 并且 秘密签名 是由一个特定的整数数组生成的，
 * 该数组唯一地包含 1 到 n 中所有不同的数字（秘密签名的长度加 1 等于 n）。
 * 例如，秘密签名 "DI" 可以由数组 [2,1,3] 或 [3,1,2] 生成，
 * 但是不能由数组 [3,2,4] 或 [2,1,3,4] 生成，因为它们都不是合法的能代表 "DI" 秘密签名 的特定串。
 * 现在你的任务是找到具有最小字典序的 [1, 2, ... n] 的排列，使其能代表输入的 秘密签名。
 *
 * 示例 1：
 * 输入： "I"
 * 输出： [1,2]
 * 解释： [1,2] 是唯一合法的可以生成秘密签名 "I" 的特定串，数字 1 和 2 构成递增关系。
 *
 * 示例 2：
 * 输入： "DI"
 * 输出： [2,1,3]
 * 解释： [2,1,3] 和 [3,1,2] 可以生成秘密签名 "DI"，
 * 但是由于我们要找字典序最小的排列，因此你需要输出 [2,1,3]。
 *
 * 注：
 * 输出字符串只会包含字符 'D' 和 'I'。
 * 输入字符串的长度是一个正整数且不会超过 10,000。
 */
public class FindPermutation {

    public static void main(String[] args) {
        System.out.println(new FindPermutation().findPermutation("DI"));
    }

    public int[] findPermutation(String s) {
        int n = s.length()+1;
        int[] ans = new int[n];
        for(int i=0;i<n;i++) {
            ans[i] = i+1;
        }
        int l = 0;
        while(l<s.length()) {
            if(s.charAt(l)=='D') {
                int r = l;
                while(r<s.length()&&s.charAt(r)=='D') {
                    r++;
                }
                swap(ans, l, r);
                l = r;
            }else {
                l++;
            }
        }
        return ans;
    }

    private void swap(int[] arr, int start, int end) {
        for(int i=start;i<end-(i-start);i++) {
            int tmp = arr[i];
            arr[i] = arr[start+end-i];
            arr[start+end-i] = tmp;
        }
    }

}
