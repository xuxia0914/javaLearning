package cn.xux.test.service.impl;

import cn.xux.test.dao.PersonMapper;
import cn.xux.test.model.Person;
import cn.xux.test.service.PersonService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public Person insert(Person person) {
        return personMapper.insertSelective(person);
    }

    @Override

    public Person get(int id) {
        return personMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(int id) {
        personMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<Person> selectPageByParams(Map<String, Object> params) {
        PageHelper.startPage((int)params.get("pageNum"), (int)params.get("pageSize"));
        List<Person> list = personMapper.selectByParams(params);
        return new PageInfo<Person>(list);
    }

}
