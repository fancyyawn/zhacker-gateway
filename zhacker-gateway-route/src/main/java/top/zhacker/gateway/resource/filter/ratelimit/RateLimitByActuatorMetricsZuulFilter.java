package top.zhacker.gateway.resource.filter.ratelimit;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.SystemPublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Optional;

/**
 * <a href="https://blog.csdn.net/tracy38/article/details/78685707"></a>
 */
@Component
public class RateLimitByActuatorMetricsZuulFilter extends ZuulFilter {

  private final RateLimiter rateLimiter = RateLimiter.create(1000.0);

  @Override
  public String filterType() {
    return FilterConstants.PRE_TYPE;
  }

  @Override
  public int filterOrder() {
    return Ordered.HIGHEST_PRECEDENCE;
  }

  @Autowired
  private SystemPublicMetrics systemPublicMetrics;

  @Override
  public boolean shouldFilter() {
// 这里可以考虑弄个限流开启的开关，开启限流返回true，关闭限流返回false，你懂的。
    Collection<Metric<?>> metrics = systemPublicMetrics.metrics();
    Optional<Metric<?>> freeMemoryMetric = metrics.stream()
        .filter(t -> "mem.free".equals(t.getName()))
        .findFirst();
// 如果不存在这个指标，稳妥起见，返回true，开启限流
    if (!freeMemoryMetric.isPresent()) {
      return true;
    }
    long freeMemory = freeMemoryMetric.get()
        .getValue()
        .longValue();
// 如果可用内存小于1000000KB，开启流控
    return freeMemory < 1000000L;
  }

  /**
   * 基于redis
   *
   if(!cacheDao.hasKey(TIME_KEY)) {
   cacheDao.putToValue(TIME_KEY, 0, 1, TimeUnit.SECONDS);
   }
   if(cacheDao.hasKey(TIME_KEY) && cacheDao.incrBy(COUNTER_KEY, 1) > 400) {
   // 抛个异常什么的
   }
   * @return
   */
  @Override
  public Object run() {
    try {
      RequestContext currentContext = RequestContext.getCurrentContext();
      HttpServletResponse response = currentContext.getResponse();
      if (!rateLimiter.tryAcquire()) {
        HttpStatus httpStatus = HttpStatus.TOO_MANY_REQUESTS;
        response.setContentType(MediaType.TEXT_PLAIN_VALUE);
        response.setStatus(httpStatus.value());
        response.getWriter().append(httpStatus.getReasonPhrase());
        currentContext.setSendZuulResponse(false);
        throw new ZuulException(
            httpStatus.getReasonPhrase(),
            httpStatus.value(),
            httpStatus.getReasonPhrase()
        );
      }
    } catch (Exception e) {
      ReflectionUtils.rethrowRuntimeException(e);
    }
    return null;
  }
}