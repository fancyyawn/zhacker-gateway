package top.zhacker.gateway.passport.validate.code.sms;

import top.zhacker.gateway.passport.properties.SecurityConstants;
import top.zhacker.gateway.passport.validate.code.ValidateCode;
import top.zhacker.gateway.passport.validate.code.impl.AbstractValidateCodeProcessor;
import top.zhacker.gateway.passport.validate.code.ValidateCode;
import top.zhacker.gateway.passport.validate.code.impl.AbstractValidateCodeProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created on 2018/1/10.
 *
 * @author zlf
 * @since 1.0
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        smsCodeSender.send(mobile, validateCode.getCode());
    }
}
