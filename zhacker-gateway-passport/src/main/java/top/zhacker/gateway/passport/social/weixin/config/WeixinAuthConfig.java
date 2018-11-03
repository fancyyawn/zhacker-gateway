package top.zhacker.gateway.passport.social.weixin.config;

import top.zhacker.gateway.passport.properties.SecurityConstants;
import top.zhacker.gateway.passport.social.SocialConnectView;
import top.zhacker.gateway.passport.social.weixin.connect.WeixinConnectionFactory;
import top.zhacker.gateway.passport.properties.SecurityConstants;
import top.zhacker.gateway.passport.social.SocialConnectView;
import top.zhacker.gateway.passport.social.weixin.connect.WeixinConnectionFactory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

import static top.zhacker.gateway.passport.properties.SecurityConstants.DEFAULT_SOCIAL_WEIXIN_PROVIDER_ID;

/**
 * Created on 2018/1/11.
 *
 * @author zlf
 * @since 1.0
 */
@Configuration
public class WeixinAuthConfig extends SocialAutoConfigurerAdapter {

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        return new WeixinConnectionFactory(SecurityConstants.DEFAULT_SOCIAL_WEIXIN_PROVIDER_ID, SecurityConstants.DEFAULT_SOCIAL_WEIXIN_APP_ID,
                SecurityConstants.DEFAULT_SOCIAL_WEIXIN_APP_SECRET);
    }

    /**
     * /connect/weixin POST请求,绑定微信返回connect/weixinConnected视图
     * /connect/weixin DELETE请求,解绑返回connect/weixinConnect视图
     * @return
     */
    @Bean({"connect/weixinConnect", "connect/weixinConnected"})
    @ConditionalOnMissingBean(name = "weixinConnectedView")
    public View weixinConnectedView() {
        return new SocialConnectView();
    }

}
