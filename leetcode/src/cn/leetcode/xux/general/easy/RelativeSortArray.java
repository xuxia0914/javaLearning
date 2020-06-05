package cn.leetcode.xux.general.easy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1122. 数组的相对排序
 * 给你两个数组，arr1 和 arr2，
 * arr2 中的元素各不相同
 * arr2 中的每个元素都出现在 arr1 中
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。
 * 未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 *
 * 示例：
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 *
 * 提示：
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * arr2 中的元素 arr2[i] 各不相同
 * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
 */
public class RelativeSortArray {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        if(arr1==null||arr1.length<2) {
            return arr1;
        }
        int[] map = new int[1002];
        for(int i=0;i<arr2.length;i++) {
            map[arr2[i]] = i+1;
        }
        Integer[] tmp = new Integer[arr1.length];
        for(int i=0;i<arr1.length;i++) {
            tmp[i] = arr1[i];
        }
        Arrays.sort(tmp, (Comparator<Integer>) (o1, o2) -> {
            if(map[o1]>0&&map[o2]>0) {
                return map[o1]-map[o2];
            }else if(map[o1]==0&&map[o2]==0) {
                return o1-o2;
            }else {
                return 0;
            }
        });
        for(int i=0;i<arr1.length;i++) {
            arr1[i] = tmp[i];
        }
        return arr1;
    }

}
