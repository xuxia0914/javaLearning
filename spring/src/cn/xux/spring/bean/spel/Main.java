package cn.xux.spring.bean.spel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring表达式语言（简称SpEL）：是一个支持运行时查询和操作对象图的强大的表达式语言；
 * 语法类似于EL：SpEL使用#{...}作为界定符，所有在大括号内的字符都将被认为是SpEL
 * SpEL为bean的属性进行动态赋值提供了便利
 * 通过SpEL可以实现：
 *      通过bean的id对bean进行引用
 *      通过方法以及引用对象中的属性
 *      计算表达式的值
 *      正大表达式匹配
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-spel.xml");

        Address address = (Address) ctx.getBean("address");
        System.out.println("address = " + address);

        Car car = (Car) ctx.getBean("car");
        System.out.println("car = " + car);

        Person person = (Person) ctx.getBean("person");
        System.out.println("person = " + person);

    }

}
