package cn.xux.thread.lambda;

import sun.awt.windows.ThemeReader;

/**
 * lambda 推导 加入参数
 */
public class LambdaTest04 {

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println("听歌");
        }).start();

        new Thread( ()->System.out.println("coding") ).start();

    }

}