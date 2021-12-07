package com.example.aishuatiserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;




@SpringBootTest
public class AishuatiServerTests {
    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }
    /*
     * HikariProxyConnection@1694102613 wrapping com.mysql.cj.jdbc.ConnectionImpl@5d5a51b1
     * */
    @Test
    void getConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

}
