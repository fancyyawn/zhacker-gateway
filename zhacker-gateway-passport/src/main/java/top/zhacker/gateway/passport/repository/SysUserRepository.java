package top.zhacker.gateway.passport.repository;

import top.zhacker.gateway.passport.domain.SysUser;

/**
 * Created on 2018/1/28 0028.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface SysUserRepository extends MerryyouRepository<SysUser> {
    /**
     * 用户名查找
     * @param username
     * @return
     */
    SysUser findByUsername(String username);
}
