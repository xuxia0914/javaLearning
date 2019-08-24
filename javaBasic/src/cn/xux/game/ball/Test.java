package cn.xux.game.ball;

import java.util.Random;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("请输入圆的半径：");
        float r = scanner.nextFloat();
        System.out.println("该圆的半径为：R="+r);
        System.out.println("该圆的周长为：C=2*3.14*"+r+"="+2*3.14*r);
        System.out.println("该圆的面积为：S=3.14*"+r+"*"+r+"="+3.14*r*r);*/

        /*while(true) {
            int i = (int)(Math.random()*2+1);
            System.out.println(i);
        }*/
        /**
         * Adouble x = 100;
         *
         * 　　B.char x = 100;
         *
         * 　　C.String x = "100";
         *
         * 　　D.int x = 100;
         */
        int x = 100;
        switch(x) {
            case 100 :
                System.out.println("One hundred");
                break;
            case 200 :
                System.out.println("Two hundred");
                break;
            case 300 :
                System.out.println( "Three hundred");
                break;
            default :
                System.out.println( "default");
        }
    }

}
