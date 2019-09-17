package cn.xux.spring.bean.annotation.repository;

import org.springframework.stereotype.Repository;

@Repository(value="userRepository")
//@Repository
public class UserRepositoryImpl2 implements UserRepository {

    @Override
    public void save() {
        System.out.println("UserRepositoryImpl2 save()...");
    }

}
