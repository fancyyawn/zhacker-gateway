package top.zhacker.ui.admin.browser.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * DATE: 2017/5/4 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@Slf4j
@RestController
public class TokensApi {

    private TokenExtractor extractor = new BearerTokenExtractor();

    @RequestMapping("/token")
    public String token(Principal user){

        OAuth2Authentication authentication = (OAuth2Authentication)user;
        log.info("{}",user);
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
        String content = JwtHelper.decode(details.getTokenValue()).getClaims();
        log.info("{}",content);
        return content;
    }
    
    

}
