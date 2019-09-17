package cn.xux.spring.bean.generic.di.service;

import cn.xux.spring.bean.generic.di.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<T> {

    @Autowired
    private BaseRepository<T> baseRepository;

    public void add() {
        System.out.println("BaseService add()...");
        System.out.println(baseRepository);
    }

}
