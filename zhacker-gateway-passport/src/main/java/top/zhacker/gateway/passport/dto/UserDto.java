package top.zhacker.gateway.passport.dto;

import top.zhacker.gateway.passport.serializer.Date2LongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import top.zhacker.gateway.passport.serializer.Date2LongSerializer;

import java.util.Date;

/**
 * Created on 2018/2/9.
 *
 * @author zlf
 * @since 1.0
 */
@Data
public class UserDto {

    private String id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮件
     */
    private String email;

    private String realName;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createDate;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateDate;

    private String roles;
}
