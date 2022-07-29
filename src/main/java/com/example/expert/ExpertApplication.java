package com.example.expert;

import com.example.expert.configurations.database.Db1Config;
import com.example.expert.configurations.database.Db2Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication
@Import({Db1Config.class, Db2Config.class})
public class ExpertApplication implements CommandLineRunner {

    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        log.info("Start application");
        new SpringApplication(ExpertApplication.class).run(args);
    }


    @Override
    public void run(String... args) throws Exception {


    }


}
