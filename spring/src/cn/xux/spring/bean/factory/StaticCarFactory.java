package cn.xux.spring.bean.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 静态工厂方法：直接调用某一个类的静态方法就可以返回bean的实例
 */
public class StaticCarFactory {

    private static Map<String, Car> cars = new HashMap<>();

    static {
        cars.put("audi", new Car("audi", "Shanghai", 240));
        cars.put("ford", new Car("ford", "Changan", 230));
    }

    //静态工厂方法
    public static Car getCar(String brand) {
        return cars.get(brand);
    }

}
