package cn.xux.spring.bean.generic.di;

import cn.xux.spring.bean.generic.di.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 泛型依赖注入
 * 父类的依赖关系，相同类型的子类是可以继承的
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-genericDI.xml");

        UserService userService = (UserService) ctx.getBean("userService");
        userService.add();
    }

}
