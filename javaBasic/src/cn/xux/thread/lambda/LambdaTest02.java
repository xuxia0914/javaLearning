package cn.xux.thread.lambda;

/**
 * lambda 推导 加入参数
 */
public class LambdaTest02 {

    public static void main(String[] args) {
        ILove love = (int a)-> {
            System.out.println("I love lambda "+a);
        };
        love.lambda(0);

        //简化1 类型可以去掉
        ILove love1 = (a)-> {
            System.out.println("I love lambda "+a);
        };
        love1.lambda(1);

        //简化2 只有一个参数
        ILove love2 = a-> {
            System.out.println("I love lambda "+a);
        };
        love2.lambda(2);

        //简化3 只有一行代码
        ILove love3 = (a)-> System.out.println("I love lambda "+a);
        love3.lambda(3);
    }

}

interface ILove {
    void lambda(int a);
}