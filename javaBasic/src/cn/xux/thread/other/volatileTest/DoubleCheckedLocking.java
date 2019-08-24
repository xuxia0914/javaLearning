package cn.xux.thread.other.volatileTest;

/**
 * DCL单例模式：对外只存在一个对象
 * 1、构造器私有化 --> 避免外部创建对象
 * 2、提供私有的静态属性 --> 存储对象的地址
 * 3、提供公共的静态方法 --> 获取属性
 */
public class DoubleCheckedLocking {

    //2、提供私有的静态属性 --> 存储对象的地址
    //没有volatile修饰其他线程可能访问到一个没有初始化的对象
    private static volatile DoubleCheckedLocking instance;

    //1、构造器私有化 --> 避免外部创建对象
    private DoubleCheckedLocking() {}

    //3、提供公共的静态方法 --> 获取属性
    public static DoubleCheckedLocking getInstance(long time) {
        if(null == instance) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new DoubleCheckedLocking();
            //1、开辟空间；2、初始化对象信息；3、返回对象的地址给引用
        }
        return instance;
    }

    //3、提供公共的静态方法 --> 获取属性
    public static DoubleCheckedLocking getInstance1(long time) {
        //再次检测
        if(null!=instance) {    //避免不必要的同步等待
            return instance;
        }
        synchronized (DoubleCheckedLocking.class) {
            if(null == instance) {
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                instance = new DoubleCheckedLocking();
                //1、开辟空间；2、初始化对象信息；3、返回对象的地址给引用
            }
        }
        return instance;
    }

    public static void main(String[] args) {

        new Thread(()->{
            //System.out.println(DoubleCheckedLocking.getInstance(500));
            System.out.println(DoubleCheckedLocking.getInstance1(500));
        }).start();

        new Thread(()->{
            //System.out.println(DoubleCheckedLocking.getInstance(1000));
            System.out.println(DoubleCheckedLocking.getInstance1(1000));
        }).start();

    }

}
