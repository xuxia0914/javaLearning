package cn.xux.algorithm.leetcode.general.midium;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
 * You are given a target value to search. If found in the array return true, otherwise return false.
 * Example 1:
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * Example 2:
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 */
public class BeforhandArrayII {

    public static boolean solution(int[] array, int target) {
        return helper(array, 0, array.length-1, target);
    }

    /**
     * @param array
     * @param start
     * @param end
     * @param target
     * @return
     */
    public static boolean helper(int[] array, int start, int end, int target) {
        if(start>end) {
            return false;
        }
        if(start==end) {
            return array[start]==target?true:false;
        }
        int m = (end+start)/2;
        int n = m+1;
        if(m>start&&array[m]==array[m-1]) {
            m--;
        }
        if(n<end&&array[n]==array[n+1]) {
            n++;
        }
        if(array[m]==array[n]) {
            if(m>start) {
                m--;
            }else if(n<end) {
                n++;
            }else if(array[m]==target) {
                return true;
            }else {
                return false;
            }
        }
        int left = array[m];
        int right = array[n];
        if(left==target||right==target) {
            return true;
        }
        if(left<right) {
            if(target > left && target < right) {
                return false;
            }else {
                boolean reuslt_left = helper(array, start, m-1, target);
                if (reuslt_left == false) {
                    return helper(array, n+1, end, target);
                } else {
                    return reuslt_left;
                }
            }
        }else {
            if(target > right && target < left) {
                boolean reuslt_left = helper(array, start, m-1, target);
                if(reuslt_left == false) {
                    return helper(array, n+1, end, target);
                }else {
                    return reuslt_left;
                }
            }else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{4,5,7,0,0,0,1,1,2}, 0));
        System.out.println(solution(new int[]{4,4,4,5,5,5,7,7,7,0,1,2}, 4));
        System.out.println(solution(new int[]{4,5,5,5,5,5,7,7,7,0,1,2,2,2,2,2}, 7));
        System.out.println(solution(new int[]{4,5,7,0,1,1,1,1,1,2}, 2));
        System.out.println(solution(new int[]{4,5,7,7,7,0,1,1,1,1,2}, 3));
        System.out.println(solution(new int[]{4,5,7,0,1,2}, 6));
    }

}
