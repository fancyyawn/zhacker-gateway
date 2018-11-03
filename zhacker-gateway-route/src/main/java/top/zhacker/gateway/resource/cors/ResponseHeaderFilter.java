package top.zhacker.gateway.resource.cors;

import com.google.common.base.Strings;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;


@Component
public class ResponseHeaderFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return SEND_RESPONSE_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
        if(Strings.isNullOrEmpty(response.getHeader("Access-Control-Allow-Origin"))){
            response.setHeader("Access-Control-Allow-Origin", "*");
        }
        return null;
    }
}