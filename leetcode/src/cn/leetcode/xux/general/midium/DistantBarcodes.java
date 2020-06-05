package cn.leetcode.xux.general.midium;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 1054. 距离相等的条形码
 * 在一个仓库里，有一排条形码，其中第 i 个条形码为 barcodes[i]。
 * 请你重新排列这些条形码，使其中两个相邻的条形码 不能 相等。
 * 你可以返回任何满足该要求的答案，此题保证存在答案。
 *
 * 示例 1：
 * 输入：[1,1,1,2,2,2]
 * 输出：[2,1,2,1,2,1]
 *
 * 示例 2：
 * 输入：[1,1,1,1,2,2,3,3]
 * 输出：[1,3,1,3,2,1,2,1]
 *
 * 提示：
 * 1 <= barcodes.length <= 10000
 * 1 <= barcodes[i] <= 10000
 */
public class DistantBarcodes {

    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int barcode : barcodes) {
            map.put(barcode, map.getOrDefault(barcode, 0)+1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> queue =
                new PriorityQueue<>(Comparator.comparingInt(o->-o.getValue()));
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(entry);
        }
        int currBarcode = 0;
        int currCnt = 0;
        int len = barcodes.length;
        for(int i=0;i<len;i+=2) {
            if(currCnt==0) {
                Map.Entry<Integer, Integer> entry = queue.poll();
                currBarcode = entry.getKey();
                currCnt = entry.getValue();
            }
            barcodes[i] = currBarcode;
            currCnt--;
        }
        for(int i=1;i<len;i+=2) {
            if(currCnt==0) {
                Map.Entry<Integer, Integer> entry = queue.poll();
                currBarcode = entry.getKey();
                currCnt = entry.getValue();
            }
            barcodes[i] = currBarcode;
            currCnt--;
        }
        return barcodes;
    }

}
