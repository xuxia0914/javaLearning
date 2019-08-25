package cn.xux.spring.bean.collection;

import cn.xux.spring.bean.Car;

import java.util.List;
import java.util.Map;

public class NewPerson {

    private String name;
    private int age;
    private Map<String, Car> cars;

    public NewPerson() {}

    public NewPerson(String name, int age, Map<String, Car> cars) {
        this.name = name;
        this.age = age;
        this.cars = cars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<String, Car> getCars() {
        return cars;
    }

    public void setCars(Map<String, Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", cars=" + cars +
                '}';
    }

}
