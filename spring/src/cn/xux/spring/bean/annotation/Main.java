package cn.xux.spring.bean.annotation;

import cn.xux.spring.bean.annotation.controller.UserController;
import cn.xux.spring.bean.annotation.repository.UserRepository;
import cn.xux.spring.bean.annotation.servie.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 配置bean方式：基于注解的方式（基于注解配置bean，基于注解装配bean的属性）
 *
 * 在classpath中组件扫描
 * 组件扫描（component scanning）：Spring能够从classpath下自动扫描，侦测和实例化具有特定注解的组件。
 * 特定组件包括(可以混用但不建议这么做)：
 * @Component：基本注解，表示了一个手Spring管理的组件
 * @Respository：标记持久层组件
 * @Service：标识服务层（业务层）组件
 * @Controller：标识表现层组件
 *
 * 对于扫描到的组件，Spring有默认的命名策略：使用非限定雷鸣，第一个字母小写，也可以在注解中通过value属性值标识组件的名称
 *
 * <context:component-scan>: base-package属性指定需要扫描的基类包，Spring容器将会扫描这个基类包中的所有类，
 * 需要扫描多个包时，可以使用逗号分隔
 * 如果仅希望扫描特定的类二非基类包下的所有类，可以使用resource-pattern属性过滤特定的类
 *
 * <context:include-filter> 子节点标识需要包含的目标类
 * <context:exclude-filter> 子节点标识要排除在外的目标类
 *
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-annotation.xml");

//        UserRepository userRepository = (UserRepository)ctx.getBean("userRepository");
//        System.out.println(userRepository);
//
//        TestObject to = (TestObject)ctx.getBean("testObject");
//        System.out.println(to);

        UserController userController = (UserController)ctx.getBean("userController");
        System.out.println(userController);
        userController.execute();


//        UserRepository userRepository = (UserRepository)ctx.getBean("userRepositoryImpl");
        /*UserRepository userRepository = (UserRepository)ctx.getBean("userRepository");
        System.out.println(userRepository);*/

//        UserService userService = (UserService)ctx.getBean("userService");
//        System.out.println(userService);

    }

}
