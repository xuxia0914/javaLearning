package cn.leetcode.xux.general.midium;

import java.util.Map;
import java.util.TreeMap;

/**
 * 954. 二倍数对数组
 * 给定一个长度为偶数的整数数组 A，
 * 只有对 A 进行重组后可以满足 “对于每个 0 <= i < len(A) / 2，
 * 都有 A[2 * i + 1] = 2 * A[2 * i]” 时，返回 true；否则，返回 false。
 *
 * 示例 1：
 * 输入：[3,1,3,6]
 * 输出：false
 *
 * 示例 2：
 * 输入：[2,1,2,6]
 * 输出：false
 *
 * 示例 3：
 * 输入：[4,-2,2,-4]
 * 输出：true
 * 解释：我们可以用 [-2,-4] 和 [2,4] 这两组组成 [-2,-4,2,4] 或是 [2,4,-2,-4]
 *
 * 示例 4：
 * 输入：[1,2,4,16,8,4]
 * 输出：false
 *
 * 提示：
 * 0 <= A.length <= 30000
 * A.length 为偶数
 * -100000 <= A[i] <= 100000
 */
public class ArrayOfDoubledPairs {

    public static void main(String[] args) {
        System.out.println(new ArrayOfDoubledPairs().canReorderDoubled(
                new int[]{4,-2,2,-4}
        ));
    }

    public boolean canReorderDoubled(int[] A) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int num : A) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        while(map.size()>0) {
            Map.Entry<Integer, Integer> entry = map.pollFirstEntry();
            int key = entry.getKey();
            int val = entry.getValue();
            int targetKey;
            int targetVal;
            if(entry.getKey()>0) {
                targetKey = entry.getKey() * 2;
            }else if(key==0) {
                if(val%2==0) {
                    continue;
                }else {
                    return false;
                }
            }else if(entry.getKey()%2==0) {
                targetKey = entry.getKey() / 2;
            }else {
                return false;
            }
            if(!map.containsKey(targetKey)) {
                return false;
            }else {
                targetVal = map.get(targetKey);
                if(targetVal<val) {
                    return false;
                }else if(targetVal==val) {
                    map.remove(targetKey);
                }else {
                    map.put(targetKey, targetVal-val);
                }
            }
        }
        return true;
    }

}
