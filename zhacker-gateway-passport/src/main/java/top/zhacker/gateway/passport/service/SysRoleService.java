package top.zhacker.gateway.passport.service;

import top.zhacker.gateway.passport.domain.Result;
import top.zhacker.gateway.passport.domain.SysRole;
import top.zhacker.gateway.passport.dto.RoleDto;

import java.util.List;

/**
 * Created on 2018/2/9.
 *
 * @author zlf
 * @since 1.0
 */
public interface SysRoleService extends MerryyouBaseService<SysRole>{

    List<RoleDto> findAlls();

    Result saveRole(String data);

    RoleDto findRole(String id);

    Result<String> delRoles(String ids);
}
