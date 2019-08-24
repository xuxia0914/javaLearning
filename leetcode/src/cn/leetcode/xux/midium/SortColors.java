package cn.leetcode.xux.midium;

/**
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * Note:
 *
 * Follow up:
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's,
 * then 1's and followed by 2's.
 * Could you come up with an one-pass algorithm using only constant space?
 */
public class SortColors {

    public static void solution(int[] colors) {
        int left = 0, right = colors.length-1;
        int i = 0, tmp;
        while(i<=right) {
            if(colors[i]==0&&i>left) {
                tmp = colors[i];
                colors[i] = colors[left];
                colors[left] = tmp;
                left++;
            }else if(colors[i]==2&&i<right) {
                tmp = colors[i];
                colors[i] = colors[right];
                colors[right] = tmp;
                right--;
            }else {
                i++;
            }
        }
    }

    public static void main(String[] args) {
//        int[] colors = new int[]{2, 0, 2, 0, 1, 1, 2, 0, 2, 1, 0};
        int[] colors = new int[]{0,1,0};
        solution(colors);
        for(int i : colors) {
            System.out.print(i+" ");
        }
    }

}
