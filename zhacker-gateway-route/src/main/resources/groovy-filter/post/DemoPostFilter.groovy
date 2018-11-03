package top.zhacker.gateway.resource.filter.monitor;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants

public class DemoPostFilter extends ZuulFilter {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DemoPostFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }


    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER + 2;
    }


    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        RequestContext context = RequestContext.getCurrentContext();

        log.info("filter execution from groovy, ip={}, url={}, summary={}", context.getRequest().getRemoteAddr(), context.getRequest().getRequestURI(), context.getFilterExecutionSummary().toString());
        return null;
    }
}