package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *Find the kth largest element in an unsorted array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * Example 1:
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthLargestElementInAnArray {

    public int solution1(int[] array, int k) {
        Arrays.sort(array);
        return array[array.length-k];
    }

    /**使用快速排序*/
    public static int solution2(List<Integer> list, int k) {
        int left = 0, right = list.size()-1;
        int key = list.get(left);
        while(left<right) {
            while(left<right&&list.get(right)<=key) {
                right--;
            }
            if(left<right) {
                list.set(left, list.get(right));
            }
            while(left<right&&list.get(left)>=key) {
                left++;
            }
            if(left<right) {
                list.set(right, list.get(left));
            }
            list.set(left, key);
        }
        if(left == k-1) {
            return list.get(left);
        }else if(left>k-1) {
            return solution2(list.subList(0, left), k);
        }else {
            return solution2(list.subList(left+1, list.size()), k-1-left);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(5);
        list.add(5);
        list.add(6);
        System.out.println(solution2(list, 4));
    }

}
