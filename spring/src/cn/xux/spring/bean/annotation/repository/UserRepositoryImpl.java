package cn.xux.spring.bean.annotation.repository;

import org.springframework.stereotype.Repository;

//@Repository(value="userRepository")
@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

    @Override
    public void save() {
        System.out.println("UserRepositoryImpl save()...");
    }

}
