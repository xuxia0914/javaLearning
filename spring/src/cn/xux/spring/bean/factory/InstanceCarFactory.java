package cn.xux.spring.bean.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 实例工厂方法：实例工厂的方法，即先需要创建工厂本身，再调用工厂的实例方法来返回bean的实例
 */
public class InstanceCarFactory {

    private Map<String, Car> cars;

    public InstanceCarFactory() {
        cars = new HashMap<>();
        cars.put("audi", new Car("audi", "Shanghai", 260));
        cars.put("ford", new Car("ford", "Changan", 270));
    }

    public Car getCar(String brand) {
        return cars.get(brand);
    }

}
