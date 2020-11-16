package cn.xux.algorithm.leetcode.general.midium;

/**
 * 406. 根据身高重建队列
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，
 * 其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 *
 * 注意：
 * 总人数少于1100人。
 *
 * 示例
 * 输入:[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * 输出:[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class QueueReconstructionByHeight {

    public int[][] reconstructQueue(int[][] people) {
        if(people==null||people.length<2) {
            return people;
        }
        sort(people, 0, people.length-1);
        for(int i=0;i<people.length;i++) {
            if(people[i][1]<i) {
                int[] tmp = people[i];
                for(int j=i;j>tmp[1];j--) {
                    people[j] = people[j-1];
                }
                people[tmp[1]] = tmp;
            }
        }
        return people;
    }

    //按照pair[0]降序， pair[1]升序排列
    public void sort(int[][] people, int start, int end) {
        if(start>=end) {
            return;
        }
        int left = start, right = end;
        int[] key = people[start];
        int[] tmp;
        while(left<right) {
            while(left<right&&(people[right][0]<key[0]||(people[right][0]==key[0]&&people[right][1]>=key[1]))) {
                right--;
            }
            while(left<right&&(people[left][0]>key[0]||(people[left][0]==key[0]&&people[left][1]<=key[1]))) {
                left++;
            }
            if(left<right) {
                tmp = people[left];
                people[left] = people[right];
                people[right] = tmp;
            }
        }
        people[start] = people[right];
        people[right] = key;
        sort(people, start, right-1);
        sort(people, right+1, end);
    }

    public static void main(String[] args) {
        QueueReconstructionByHeight q = new QueueReconstructionByHeight();
        System.out.println(q.reconstructQueue(new int[][]{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}}));
    }

}
