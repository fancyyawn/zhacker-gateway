package top.zhacker.gateway.passport.handle;

import top.zhacker.gateway.passport.domain.Result;
import top.zhacker.gateway.passport.enums.ResultEnum;
import top.zhacker.gateway.passport.exception.PersonException;
import top.zhacker.gateway.passport.utils.ResultUtil;
import top.zhacker.gateway.passport.validate.code.ValidateCodeException;
import lombok.extern.slf4j.Slf4j;
import top.zhacker.gateway.passport.exception.PersonException;
import top.zhacker.gateway.passport.validate.code.ValidateCodeException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2017/11/7.
 *
 * @author zlf
 * @since 1.0
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof PersonException) {
            PersonException personException = (PersonException) e;
            return ResultUtil.error(personException.getCode(), personException.getMessage());
        } else if (e instanceof ValidateCodeException) {
            ValidateCodeException validateCodeException = (ValidateCodeException) e;
            return ResultUtil.error(validateCodeException.getCode(), validateCodeException.getMessage());
        } else if (e instanceof AccessDeniedException) {
            return ResultUtil.error(ResultEnum.ACCESS_DENIED.getCode(), ResultEnum.ACCESS_DENIED.getMsg());
        } else {
            log.error("【系统异常】{}", e);
            return ResultUtil.error(-1, "未知错误");
        }
    }

}
