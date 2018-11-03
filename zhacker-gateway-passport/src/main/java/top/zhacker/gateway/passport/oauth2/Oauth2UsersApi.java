package top.zhacker.gateway.passport.oauth2;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * DATE: 2017/4/30 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@RestController
public class Oauth2UsersApi {
    /**
     * 定义用户信息
     *
     * @param authentication
     * @return
     */
    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public Object getUser(OAuth2Authentication authentication) {
        return authentication;
    }
    
}
