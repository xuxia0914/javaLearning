package cn.xux.spring.bean.factorybean;

import org.springframework.beans.factory.FactoryBean;

//自定义一个的类实现FactoryBean接口
public class CarFactoryBean implements FactoryBean<Car> {

    private String brand;

    public void setBrand(String brand) {
        this.brand = brand;
    }

    //返回bean的对象
    @Override
    public Car getObject() throws Exception {
        return new Car(this.brand, "Bolin", 500000);
    }

    /**
     * 返回的bean的类型
     * @return
     */
    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
