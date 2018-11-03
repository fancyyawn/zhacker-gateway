package top.zhacker.gateway.passport.service;

import top.zhacker.gateway.passport.domain.Result;
import top.zhacker.gateway.passport.domain.SysUser;
import top.zhacker.gateway.passport.dto.UserDto;

import java.util.List;

/**
 * Created on 2018/2/1 0001.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface SysUserService {

    SysUser save(SysUser user);

    SysUser findByUsername(String username);

    List<UserDto> findAll();

    UserDto findOne(String id);

    Result save(String data);

    Result<String> delUsers(String ids);
}
