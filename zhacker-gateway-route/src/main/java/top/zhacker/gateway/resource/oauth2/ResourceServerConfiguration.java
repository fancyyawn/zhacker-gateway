package top.zhacker.gateway.resource.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .authorizeRequests()
            .antMatchers("/health").permitAll()
            .antMatchers("/routes").permitAll()
            .antMatchers("/route/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
            .anyRequest().authenticated();
        // @formatter:on
    }
}
