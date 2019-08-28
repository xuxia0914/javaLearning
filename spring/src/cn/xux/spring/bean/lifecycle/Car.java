package cn.xux.spring.bean.lifecycle;

public class Car {

    private String brand;

    public Car() {
        System.out.println("Car's constructor");
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        System.out.println("setBrand");
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                '}';
    }

    public void init() {
        System.out.println("Car's init method");
    }

    public void destory() {
        System.out.println("Car's destory method");
    }

}
