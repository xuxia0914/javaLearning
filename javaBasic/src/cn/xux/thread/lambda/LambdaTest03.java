package cn.xux.thread.lambda;

/**
 * lambda 推导 加入参数
 */
public class LambdaTest03 {

    public static void main(String[] args) {
        IInterest interest1 = (int a, int b)-> {
            System.out.println("I like lambda-->" + (a+b));
            return a+b;
        };
        System.out.println(interest1.lambda(1, 1));

        IInterest interest2 = (a, b)-> {
            System.out.println("I like lambda-->" + (a+b));
            return a+b;
        };
        System.out.println(interest1.lambda(2, 2));

        IInterest interest3 = (a, b)-> a+b;
        System.out.println(interest3.lambda(3, 3));

    }

}

interface IInterest {
    int lambda(int a, int b);
}

class Interest implements IInterest {

    @Override
    public int lambda(int a, int c) {
        System.out.println("I like lambda-->" + (a+c));
        return a+c;
    }

}