package cn.xux.algorithm.leetcode.vip.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 251. 展开二维向量
 * 请设计并实现一个能够展开二维向量的迭代器。该迭代器需要支持 next 和 hasNext 两种操作。、
 *
 * 示例：
 * Vector2D iterator = new Vector2D([[1,2],[3],[4]]);
 * iterator.next(); // 返回 1
 * iterator.next(); // 返回 2
 * iterator.next(); // 返回 3
 * iterator.hasNext(); // 返回 true
 * iterator.hasNext(); // 返回 true
 * iterator.next(); // 返回 4
 * iterator.hasNext(); // 返回 false
 *
 * 注意：
 * 请记得 重置 在 Vector2D 中声明的类变量（静态变量），
 * 因为类变量会 在多个测试用例中保持不变，影响判题准确。请 查阅 这里。
 * 你可以假定 next() 的调用总是合法的，即当 next() 被调用时，二维向量总是存在至少一个后续元素。
 */
public class Flatten2DVector {

    public static void main(String[] args) {
        List<Integer> list0 = new ArrayList<>();
        list0.add(1);
        list0.add(2);
        List<Integer> list1 = new ArrayList<>();
        list1.add(3);
        List<Integer> list2 = new ArrayList<>();
        list2.add(4);
        list2.add(5);
        list2.add(6);
        List<List<Integer>> listOfList = new ArrayList<>();
        listOfList.add(list0);
        listOfList.add(list1);
        listOfList.add(list2);
        Vector2D vector2D = new Vector2D(listOfList);
        while(vector2D.hasNext()) {
            System.out.println(vector2D.next());
        }
    }

}

class Vector2D {
    List<List<Integer>> listOfList;
    int i;
    int j;

    Vector2D(List<List<Integer>> listOfList) {
        this.listOfList = listOfList;
        this.i = 0;
        this.j = 0;
    }

    public boolean hasNext() {
        return this.i<this.listOfList.size();
    }

    public int next() {
        int ans = this.listOfList.get(this.i).get(this.j++);
        while(i<this.listOfList.size()&&this.j==this.listOfList.get(this.i).size()) {
            this.i++;
            this.j=0;
        }
        return ans;
    }

}
