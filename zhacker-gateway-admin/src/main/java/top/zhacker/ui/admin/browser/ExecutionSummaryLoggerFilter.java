package top.zhacker.ui.admin.browser;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class ExecutionSummaryLoggerFilter extends ZuulFilter {
  
  @Override
  public String filterType() {
    return FilterConstants.POST_TYPE;
  }
  
  
  @Override
  public int filterOrder() {
    return FilterConstants.SEND_RESPONSE_FILTER_ORDER + 1;
  }
  
  
  @Override
  public boolean shouldFilter() {
    return true;
  }
  
  @Override
  public Object run() {
    
    RequestContext context = RequestContext.getCurrentContext();
    
    log.info("filter execution summary={}", context.getFilterExecutionSummary().toString());
    return null;
  }
}