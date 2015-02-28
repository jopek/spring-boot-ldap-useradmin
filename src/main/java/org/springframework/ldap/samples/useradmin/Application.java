package org.springframework.ldap.samples.useradmin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.ldap.support.NameSerializer;

@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = "org.springframework.ldap.samples.useradmin")
public class Application {

  Logger LOG = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public Jackson2ObjectMapperBuilder jacksonBuilder() {
    LOG.info("Jackson2ObjectMapperBuilder bean used!");

    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
    builder.serializers(new NameSerializer());
    builder.indentOutput(true);
    return builder;
  }

}

