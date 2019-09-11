package cn.xux.spring.bean.annotation.servie;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void add() {
        System.out.println("UserService add()...");
    }

}
