package top.zhacker.gateway.resource.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class RefreshRouteController {
    @Autowired
    RefreshRouteService refreshRouteService;
    @Autowired
    ZuulHandlerMapping zuulHandlerMapping;
 
    @GetMapping("/route/refreshRoute")
    public String refresh() {
        refreshRouteService.refreshRoute();
        return "refresh success";
    }
 
    @RequestMapping("/route/watchRoute")
    public Object watchNowRoute() {
        //可以用debug模式看里面具体是什么
        Map<String, Object> handlerMap = zuulHandlerMapping.getHandlerMap();
        return handlerMap;
    }
 
}
