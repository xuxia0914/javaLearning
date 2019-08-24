package cn.leetcode.xux.easy;

import java.util.ArrayList;
import java.util.List;

/**
 *Given an array, rotate the array to the right by k steps, where k is non-negative.
 * Example 1:
 * Input: [1,2,3,4,5,6,7] and k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 * Input: [-1,-100,3,99] and k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 * Note:
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 */
public class RotateArray {

    public void rotate(int[] nums, int k) {
        if(nums==null||nums.length==1||k%nums.length==0) {
            return;
        }
        int length = nums.length;
        k = k%length;
        int tmp = nums[length-1];
        for(int j=0;j<length-1;j++) {
            nums[((j+1)*length-1-j*k)%length] = nums[((j+1)*length-1-j*k-k)%length];
        }
        nums[k-1] = tmp;
    }

    /**O(1)space  O(kn)memory*/
    public static List<Integer> solution1(List<Integer> array, int k) {
        if(array==null||array.size()==1||k%array.size()==0) {
            return array;
        }
        int length = array.size();
        k = k%length;
        for(int i=0;i<k;i++) {
            int tmp = array.get(length-1);
            for(int j=length-1;j>0;j--) {
                array.set(j, array.get(j-1));
            }
            array.set(0, tmp);
        }
        return array;
    }

    /**O(1)space  O(n)memory*/
    public static List<Integer> solution2(List<Integer> array, int k) {
        if(array==null||array.size()==1||k%array.size()==0) {
            return array;
        }
        int length = array.size();
        k = k%length;
        int tmp2 = length-1;
        int tmp1 = array.get(tmp2);
        for(int j=0;j<length;j++) {
            if((j*length+tmp2-j*k-k)%length==tmp2) {
                array.set((j*length+tmp2-j*k)%length, tmp1);
                tmp1 = array.get(--tmp2);
            }else {
                array.set((j*length+tmp2-j*k)%length, array.get((j*length+tmp2-j*k-k)%length));
            }
        }
        return array;
    }

    /**O(k)space  O(n+k)memory*/
    public static List<Integer> solution3(List<Integer> array, int k) {
        if(array==null||array.size()==1||k%array.size()==0) {
            return array;
        }
        int length = array.size();
        k = k%length;
        List<Integer> tmp = new ArrayList<Integer>();
        for(int i=0;i<k;i++) {
            tmp.add(array.get(length-k+i));
        }
        for(int i=length-1;i>k-1;i--) {
            array.set(i, array.get(i-k));
        }
        for(int i=0;i<tmp.size();i++) {
            array.set(i, tmp.get(i));
        }
        return array;
    }

    public static void main(String[] args) {
        List<Integer> array = new ArrayList<Integer>();
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(6);
//        array.add(7);
//        System.out.println(solution1(array, 1));
//        System.out.println(solution1(array, 6));
//        System.out.println(solution1(array, 3));
//        System.out.println(solution1(array, 7));
//        System.out.println(solution1(array, 4));
//        System.out.println(solution2(array, 1));
//        System.out.println(solution2(array, 6));
        System.out.println(solution2(array, 2));
        System.out.println(solution2(array, 7));
        System.out.println(solution2(array, 4));
//        System.out.println(solution3(array, 1));
//        System.out.println(solution3(array, 6));
//        System.out.println(solution3(array, 3));
//        System.out.println(solution3(array, 7));
//        System.out.println(solution3(array, 4));
    }

}
