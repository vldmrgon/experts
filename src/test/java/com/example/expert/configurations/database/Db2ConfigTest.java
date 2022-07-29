package com.example.expert.configurations.database;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Db2Config.class)
@TestPropertySource("classpath:test-application.properties")
class Db2ConfigTest {

    @Autowired
    private Db2Config db2Configuration;

    @Test
    void testDataSourceProperties() throws InterruptedException {
        DataSourceProperties dataSP = db2Configuration.dbDataSourceProperties();
        assertEquals("org.h2.Driver", dataSP.getDriverClassName());
        assertEquals("jdbc:h2:mem:testDb", dataSP.getUrl());
        assertEquals("root", dataSP.getUsername());
        assertEquals("r00t", dataSP.getPassword());
    }

    @Test
    void testHikariDataSource() {
        HikariDataSource hikariDS = db2Configuration.dbHikariDataSource(db2Configuration.dbDataSourceProperties());
        assertEquals(1111, hikariDS.getConnectionTimeout());
        assertEquals(1888888, hikariDS.getMaxLifetime());
        assertEquals(3, hikariDS.getMaximumPoolSize());
        assertEquals("Pool2", hikariDS.getPoolName());
    }

    @Test
    void testJpaProperties() {
        JpaProperties jpaP = db2Configuration.dbJpaProperties();
        assertFalse(jpaP.isShowSql());
        assertFalse(jpaP.isGenerateDdl());
        assertFalse(jpaP.getOpenInView());
    }

    @Test
    void testEntityManagerFactory() {

    }

    @Test
    void dbTransactionManager() {
        
    }


}

