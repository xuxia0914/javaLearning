package cn.xux.test.service;

import cn.xux.test.model.Person;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface PersonService {

    String get();

    Person insert(Person person);

    Person get(int id) ;

    void delete(int id);

    PageInfo<Person> selectPageByParams(Map<String, Object> params);

}
