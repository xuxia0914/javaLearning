package cn.xux.test.service.impl;

import cn.xux.test.dao.PersonMapper;
import cn.xux.test.model.Person;
import cn.xux.test.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonMapper personMapper;

    private static final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Override
    public String get() {
        log.info("a log from TestServiceImpl.get()");
        return "result from TestServiceImpl.get()";
    }

    @Override
    public int insert(Person person) {
        return personMapper.insertSelective(person);
    }

    @Override
    @Cacheable(value="laborunion_person",key="#id")
    public Person get(int id) {
        return personMapper.selectByPrimaryKey(id);
    }

    @Override
    @CacheEvict(value="laborunion_person",key="#id")
    public void delete(int id) {
        personMapper.deleteByPrimaryKey(id);
    }
}
