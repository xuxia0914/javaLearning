package cn.xux.spring;

import cn.xux.spring.bean.Car;
import cn.xux.spring.bean.collection.DataSource;
import cn.xux.spring.bean.collection.NewPerson;
import cn.xux.spring.bean.collection.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main2 {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        Person person3 = (Person)ctx.getBean("person3");
        System.out.println("person3 = " + person3);

        NewPerson person4 = (NewPerson)ctx.getBean("person4");
        System.out.println("person4 = " + person4);

        DataSource dataSource = (DataSource)ctx.getBean("dataSource");
        System.out.println("dataSource = " + dataSource);

        List<Car> carList = (List)ctx.getBean("cars");
        System.out.println("carList = " + carList);

        Person person5 = (Person)ctx.getBean("person5");
        System.out.println("person5 = " + person5);

        Person person6 = (Person)ctx.getBean("person6");
        System.out.println("person6 = " + person6);

        Person person7 = (Person)ctx.getBean("person7");
        System.out.println("person7 = " + person7);

        /*Person person8 = (Person)ctx.getBean("person8");
        System.out.println(person8);*/

        Person person9 = (Person)ctx.getBean("person9");
        System.out.println("person9 = " + person9);

        Person person10 = (Person)ctx.getBean("person10");
        System.out.println("person10 = " + person10);

    }

}
