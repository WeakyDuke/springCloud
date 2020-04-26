package com.duke.user.sysuser.service;

import com.duke.user.sysuser.model.SysUser;

/**
 * @author duke
 * @version 1.0
 * @Description 用户接口
 * @date 2019/7/18 10:58
 */
public interface SysUserService {

    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser selectByUname(String uname);
}
