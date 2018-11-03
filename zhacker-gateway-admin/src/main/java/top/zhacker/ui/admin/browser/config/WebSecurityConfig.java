package top.zhacker.ui.admin.browser.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * Created by zhanghecheng on 2018/4/1.
 */
//@Configuration
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .logout()
        .and()
        .authorizeRequests()
        .antMatchers("/index.html", "/home.html", "/", "/login").permitAll()
        .anyRequest().authenticated()
        .and()
        .csrf().disable();
        //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
  }
}
