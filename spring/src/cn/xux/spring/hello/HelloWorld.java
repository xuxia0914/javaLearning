package cn.xux.spring.hello;

public class HelloWorld {

    private String name;

    public HelloWorld() {
        System.out.println("HelloWorld's constructor...");
    }

    public void setName2(String name) {
        System.out.println("set name: " + name);
        this.name = name;
    }

    public void hello() {
        System.out.println("Hello, " + name);
    }

}
