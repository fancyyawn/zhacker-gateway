package top.zhacker.gateway.resource.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;

@Configuration
public class CustomRouteConfig {
 
    @Autowired
    ZuulProperties zuulProperties;
    @Autowired
    ServerProperties server;

    @Autowired
    JdbcTemplate jdbcTemplate;
 
    @Bean
    public CustomRouteLocator routeLocator() {
        CustomRouteLocator routeLocator = new CustomRouteLocator(this.server.getServletPrefix(), this.zuulProperties);
        return routeLocator;
    }

    @PostConstruct
    public void init(){
        routeLocator().setJdbcTemplate(jdbcTemplate);
        routeLocator().refresh();
    }
 
}
