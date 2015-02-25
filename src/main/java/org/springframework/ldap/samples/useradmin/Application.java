package org.springframework.ldap.samples.useradmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = "org.springframework.ldap.samples.useradmin")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}

