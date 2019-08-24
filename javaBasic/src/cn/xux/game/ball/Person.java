package cn.xux.game.ball;

public class Person {

    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void  dispaly() {
        System.out.println("姓名：" + this.name + "\n年龄："+this.age);
    }

    public static void main(String[] args) {
        Person p1 = new Person("徐若谷", 1);
        p1.dispaly();
    }

}
