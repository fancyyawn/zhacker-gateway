package top.zhacker.ui.admin.browser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * Created by zhanghecheng on 2018/4/1.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableOAuth2Sso
public class AdminBrowserApp  extends WebSecurityConfigurerAdapter {

  public static void main(String[] args){
    SpringApplication.run(AdminBrowserApp.class, args);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .logout()
        .and()
        .authorizeRequests()
        .antMatchers("/index.html", "/home.html", "/", "/login").permitAll()
        .anyRequest().authenticated()
        .and()
        .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
  }
}
