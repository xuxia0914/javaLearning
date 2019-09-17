package cn.xux.spring.bean.annotation.servie;

import cn.xux.spring.bean.annotation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Autowired注解的变量会自动装配bean名称与变量名相同的bean
 * 也可以修饰set方法，set或装配bean名称与方法入参名相同的bean
 */
@Service
public class UserService {

//    @Autowired
    private UserRepository userRepository;

    @Autowired
    private void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void add() {
        System.out.println("UserService add()...");
        userRepository.save();
    }

}
