package cn.xux.spring.bean.spel;

public class Person {

    private String name;
    private int age;
    private Car car;
    //引用Address bean的city属性
    private String city;
    //根据car的price确定info：car的price大于300000，金领 否则白领
    private String info;

    public Person() {}

    public Person(String name, int age, Car car) {
        this.name = name;
        this.age = age;
        this.car = car;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", car=" + car +
                ", city='" + city + '\'' +
                ", info='" + info + '\'' +
                '}';
    }

}
