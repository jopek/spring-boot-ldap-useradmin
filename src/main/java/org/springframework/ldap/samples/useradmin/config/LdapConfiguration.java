package org.springframework.ldap.samples.useradmin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.repository.config.EnableLdapRepositories;

/**
 * Created with IntelliJ IDEA.
 * User: pron
 * Date: 19.01.15
 * Time: 00:12
 * To change this template use File | Settings | File Templates.
 */

@PropertySource("ldap.properties")
@EnableLdapRepositories(basePackages = "org.springframework.ldap.samples.useradmin.domain")
@Configuration
public class LdapConfiguration {

  @Autowired
  private Environment env;

  @Bean
  public LdapContextSource contextSource() {
    LdapContextSource contextSource = new LdapContextSource();
    contextSource.setUrl(env.getRequiredProperty("sample.ldap.url"));
    contextSource.setBase(env.getRequiredProperty("sample.ldap.base"));
    contextSource.setUserDn(env.getRequiredProperty("sample.ldap.user"));
    contextSource.setPassword(env.getRequiredProperty("sample.ldap.password"));
    return contextSource;
  }

  @Bean
  public LdapTemplate ldapTemplate() {
    return new LdapTemplate(contextSource());
  }

}
