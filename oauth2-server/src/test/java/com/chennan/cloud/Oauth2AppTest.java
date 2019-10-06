package com.chennan.cloud;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Oauth2AppTest {

    @Autowired
    DataSource dataSource;

    @Test
    public void test() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

}
