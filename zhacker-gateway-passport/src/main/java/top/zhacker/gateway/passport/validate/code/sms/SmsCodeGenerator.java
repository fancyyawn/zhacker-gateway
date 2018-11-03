package top.zhacker.gateway.passport.validate.code.sms;

import top.zhacker.gateway.passport.properties.SecurityProperties;
import top.zhacker.gateway.passport.validate.code.ValidateCode;
import top.zhacker.gateway.passport.validate.code.ValidateCodeGenerator;
import top.zhacker.gateway.passport.validate.code.ValidateCode;
import top.zhacker.gateway.passport.validate.code.ValidateCodeGenerator;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created on 2018/1/10.
 *
 * @author zlf
 * @since 1.0
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }
}
