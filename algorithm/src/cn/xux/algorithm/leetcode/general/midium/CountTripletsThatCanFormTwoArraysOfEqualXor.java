package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 1442. 形成两个异或相等数组的三元组数目
 * 给你一个整数数组 arr 。
 * 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
 * a 和 b 定义如下：
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * 注意：^ 表示 按位异或 操作。
 * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
 *
 * 示例 1：
 * 输入：arr = [2,3,1,6,7]
 * 输出：4
 * 解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
 *
 * 示例 2：
 * 输入：arr = [1,1,1,1,1]
 * 输出：10
 *
 * 示例 3：
 * 输入：arr = [2,3]
 * 输出：0
 *
 * 示例 4：
 * 输入：arr = [1,3,5,7,9]
 * 输出：3
 *
 * 示例 5：
 * 输入：arr = [7,11,12,9,5,2,7,17,22]
 * 输出：8
 *
 * 提示：
 * 1 <= arr.length <= 300
 * 1 <= arr[i] <= 10^8
 */
public class CountTripletsThatCanFormTwoArraysOfEqualXor {

    public static void main(String[] args) {
        System.out.println(new CountTripletsThatCanFormTwoArraysOfEqualXor()
                .countTriplets1(new int[]{1,1,1,1,1}));
//                .countTriplets1(new int[]{2,3,1,6,7}));
    }

    public int countTriplets(int[] arr) {
        int n = arr.length;
        int[] preXors = new int[n+1];
        for(int i=1;i<=n;i++) {
            preXors[i] = preXors[i-1]^arr[i-1];
        }
        int ans = 0;
        for(int k=2;k<=n;k++) {
            for(int i=k-1;i>0;i--) {
                if(preXors[k]==preXors[i-1]) {
                    ans += k-i;
                }
            }
        }
        return ans;
    }

    public int countTriplets1(int[] arr) {
        int n = arr.length;
        int ans = 0;
        int curXor = 0;
        // int[0]:出现次数，int[1]：多个索引（i+1）累加和
        Map<Integer, int[]> map = new HashMap<>();
        map.put(0, new int[]{1,0});
        for(int i=0;i<n;i++) {
            curXor ^= arr[i];
            if(map.containsKey(curXor)) {
                int[] pre = map.get(curXor);
                ans += pre[0]*i-pre[1];
                pre[0]++;
                pre[1] += i+1;
            }else {
                int[] pre = new int[2];
                pre[0] = 1;
                pre[1] = i+1;
                map.put(curXor, pre);
            }
        }
        return ans;
    }

}
