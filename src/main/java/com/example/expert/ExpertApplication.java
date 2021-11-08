package com.example.expert;

import com.example.expert.configurations.database.Db1Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({Db1Config.class})
@Slf4j
public class ExpertApplication {

  public static void main(String[] args) {
    log.debug("Start application");
    new SpringApplication(ExpertApplication.class).run(args);
  }

}
