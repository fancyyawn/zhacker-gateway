package top.zhacker.gateway.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


/**
 * Created by zhacker.
 * Time 2017/9/17 上午11:51
 */
@SpringBootApplication
@EnableZuulProxy
public class GateRouteApp {
  
  public static void main(String[] args){
      SpringApplication.run(GateRouteApp.class, args);
  }

}
