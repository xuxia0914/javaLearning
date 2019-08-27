package cn.xux.spring;

import cn.xux.spring.hello.Person;
import cn.xux.spring.hello.Car;
import cn.xux.spring.hello.HelloWorld;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        /*//创建 HelloWorld 的一个对象
        HelloWorld helloWorld = new HelloWorld();
        //为 name 属性赋值
        helloWorld.setName("Xu Xia");*/

        //1、创建Spring的IOC容器对象
        //ApplicationContext标识一个IOC容器
        //ApplicationContext两个主要实现类：ClassPathXmlApplicationContext和FileSystemXmlApplicationContext
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        //2、从IOC容器中获取bean实例
        //利用id定位到IOC容器中的bean
        HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");

        //利用类型定位IOC容器中的bean，但是要求IOC容器中只有一个该类型的bean
//        HelloWorld helloWorld1 = ctx.getBean(HelloWorld.class);
//        helloWorld1.hello();

        //调用hello方法
        helloWorld.hello();

        Car car = (Car)ctx.getBean("car");
        System.out.println(car);

        Car car2 = (Car)ctx.getBean("car2");
        System.out.println(car2);

        Person person = (Person)ctx.getBean("person");
        System.out.println(person);

        Person person2 = (Person)ctx.getBean("person2");
        System.out.println(person2);

    }

}
