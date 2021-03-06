package cn.xux.spring.bean.properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-properties.xml");

        DataSource dataSource = (DataSource) ctx.getBean("dataSource");

        System.out.println(dataSource.getConnection());
    }

}
