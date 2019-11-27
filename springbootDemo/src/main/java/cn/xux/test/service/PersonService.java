package cn.xux.test.service;

import cn.xux.test.model.Person;

public interface PersonService {

    String get();

    int insert(Person person);

    Person get(int id) ;

    void delete(int id);

}
