package cn.leetcode.xux.general.hard;

/**
 * 493. 翻转对
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 * 你需要返回给定数组中的重要翻转对的数量。
 *
 * 示例 1:
 * 输入: [1,3,2,3,1]
 * 输出: 2
 *
 * 示例 2:
 * 输入: [2,4,3,5,1]
 * 输出: 3
 *
 * 注意:
 * 给定数组的长度不会超过50000。
 * 输入数组中的所有数字都在32位整数的表示范围内。
 */
public class ReversePairs {

    //归并排序
    public int reversePairs(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        return mergeSort(nums,0,nums.length-1);
    }

    public int mergeSort(int[] nums,int left,int right){
        if(right <= left ){
            return 0;
        }
        int mid = (left+right) >> 1;
        int count = mergeSort(nums,left,mid)+mergeSort(nums,mid+1,right);
        // 中间数组用于合并
        int[] cache = new int[right-left+1];
        int i = left, j = mid+1, k = 0, tmp = left;
        while(j <= right){
            while(tmp <= mid && nums[tmp] <= 2 * (long)nums[j]) tmp++;
            while(i <= mid   &&  nums[i]  <   nums[j] ) cache[k++] = nums[i++];
            cache[k++] = nums[j++];
            count += mid - tmp + 1 ;
        }
        while(i <= mid) cache[k++] = nums[i++];
        System.arraycopy(cache,0,nums,left,right- left +1);
        return count;
    }

    //TLE
    public int reversePairs1(int[] nums) {
        if(nums==null||nums.length<2) {
            return 0;
        }
        int n = nums.length;
        BTreeNode root = new BTreeNode(nums[0]);
        int ans = 0;
        for(int i=1;i<n;i++) {
            if(nums[i]<Integer.MIN_VALUE/2) {
                ans += i;
            }else if(nums[i]<=Integer.MAX_VALUE/2) {
                int tar = 2*nums[i];
                BTreeNode curr = root;
                while(curr!=null) {
                    if(curr.val>tar) {
                        ans += curr.cnt+curr.rightCnt;
                        curr = curr.left;
                    }else if(curr.val==tar) {
                        ans += curr.rightCnt;
                        break;
                    }else {
                        curr = curr.right;
                    }
                }
            }
            add(root, nums[i]);
        }
        return ans;
    }

    private void add(BTreeNode root, int val) {
        if(root.val==val) {
            root.cnt++;
        }else if(root.val>val) {
            if(root.left==null) {
                BTreeNode left = new BTreeNode(val);
                root.left = left;
            }else {
                add(root.left, val);
            }
        }else {
            root.rightCnt++;
            if(root.right==null) {
                BTreeNode right = new BTreeNode(val);
                root.right = right;
            }else {
                add(root.right, val);
            }
        }
    }

}

class BTreeNode {

    int val;
    int cnt;
    //右子树节点个数
    int rightCnt;
    BTreeNode left;
    BTreeNode right;

    BTreeNode(int val) {
        this.val = val;
        this.cnt = 1;
        this.rightCnt = 0;
        this.left = null;
        this.right = null;
    }

}
