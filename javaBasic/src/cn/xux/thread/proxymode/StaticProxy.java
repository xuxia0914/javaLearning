package cn.xux.thread.proxymode;

/**
 * 静态代理
 * 1、公共接口
 * 2、真实角色
 * 3、代理角色
 */
public class StaticProxy {

    public static void main(String[] args) {
        new WeddingCompany(new You()).happyMarry();
    }

}

interface Marry {
    void happyMarry();
}

class You implements Marry {

    @Override
    public void happyMarry() {
        System.out.println("你结婚了！");
    }

}

class WeddingCompany implements Marry {

    private Marry you;

    WeddingCompany(Marry you) {
        this.you = you;
    }

    @Override
    public void happyMarry() {
        prepare();
        this.you.happyMarry();
        end();
    }

    void prepare() {
        System.out.println("婚前准备");
    }

    void end() {
        System.out.println("婚后工作");
    }

}