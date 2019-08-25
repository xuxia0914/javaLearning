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
        System.out.println(person3);

        NewPerson person4 = (NewPerson)ctx.getBean("person4");
        System.out.println(person4);

        DataSource ds = (DataSource)ctx.getBean("dataSource");
        System.out.println(ds);

        List<Car> list = (List)ctx.getBean("cars");
        System.out.println(list);

        Person person5 = (Person)ctx.getBean("person5");
        System.out.println(person5);

        Person person6 = (Person)ctx.getBean("person6");
        System.out.println(person6);

    }

}
