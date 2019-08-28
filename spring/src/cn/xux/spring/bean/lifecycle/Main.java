package cn.xux.spring.bean.lifecycle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring IOC容器可以管理Bean的生命周期，Spring允许在Bean生命周期的特定点执行定制的任务
 * Spring IOC容器对Bean的生命周期管理的过程：
 *      通过构造器或者工厂方法创建Bean实例
 *      为Bean的属性设置值和对其他Bean的引用
 *      调用Bean的初始化方法
 *      Bean可以使用了
 *      当容器关闭时，调用Bean的销毁方法
 * 在Bean的声明里设置init-method和destory-method属性，为Bean指定初始化和销毁方法
 *
 * 创建Bean后置处理器
 * Bean后置处理器允许在调用初始化方法前后对Bean进行额外的处理
 * Bean后置处理器对IOC容器里所有Bean实例逐一处理，而非单一实例，其典型应用是：Bean属性的正确性或根据特定的标准更改Bean的属性
 * 对Bean后置处理器而言，需要实现Interface BeanPostProcessor接口，
 * 在初始化方法被调用前后，Spring将把每个Bean实例分别传递给上述接口的两个方法
 *
 *
 * 创建Bean后置处理器的Bean的生命周期
 *      通过构造器或者工厂方法创建Bean实例
 *      为Bean的属性设置值和对其他Bean的引用
 *      *将Bean实例传递给Bean后置处理器的postProcessBeforeInitialization方法
 *      调用Bean的初始化方法
 *      *将Bean实例传递给Bean后置处理器的postProcessAfterInitialization方法
 *      Bean可以使用了
 *      当容器关闭时，调用Bean的销毁方法
 */
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-lifecycle.xml");

        Car car = (Car) ctx.getBean("car");
        System.out.println(car);

        //关闭IOC容器
        ctx.close();
    }

}
