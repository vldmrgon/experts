package com.example.expert;

import com.example.expert.configurations.database.Db1Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({Db1Config.class})
public class ExpertApplication {

  public static void main(String[] args) {
    new SpringApplication(ExpertApplication.class).run(args);
  }

}
