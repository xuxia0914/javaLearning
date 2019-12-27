package cn.xux.test.dao;

import cn.xux.test.model.Person;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

@Mapper
public interface PersonMapper {

    @CacheEvict(value="person", key="#id")
    int deleteByPrimaryKey(Integer id);

    Person insertSelective(Person record);

    @Cacheable(value="person", key="#id")
    Person selectByPrimaryKey(Integer id);

    List<Person> selectByParams(Map<String, Object> params);

}