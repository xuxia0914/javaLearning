package cn.xux.spring.bean.factorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-factorybean.xml");
        Car car1 = (Car) ctx.getBean("car1");
        System.out.println(car1);
    }

}
