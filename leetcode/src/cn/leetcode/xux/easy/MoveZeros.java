package cn.leetcode.xux.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class MoveZeros {

    public static void solution1(Integer[] array) {
        if(array==null||array.length<2) {
            return;
        }
        int index=0;
        for(int i=0;i<array.length;i++) {
            if(array[i]!=0) {
                array[index++] = array[i];
            }
        }
        for(int i=index;i<array.length;i++) {
            array[i] = 0;
        }

    }

    public static void solution2(Integer[] array) {
        if(array==null||array.length<2) {
            return;
        }
        int left=0, right=1;
        while(left<array.length&&right<array.length&&left<right) {
            if(array[left]==0&&array[right]!=0) {
                int tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;
            }
            if(array[left]!=0) {
                left++;
            }
            if(array[right]==0) {
                right++;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{0, 1, 0, 3, 12};
        solution1(array);
        List<Integer> list = new ArrayList<Integer>();
        list = Arrays.asList(array);
        System.out.println(list);
    }

}
