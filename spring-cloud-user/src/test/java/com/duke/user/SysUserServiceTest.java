package com.duke.user;

import com.alibaba.fastjson.JSON;
import com.duke.UserApplicationTest;
import com.duke.user.sysuser.model.SysUser;
import com.duke.user.sysuser.service.SysUserService;
import com.duke.utils.MD5Utils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author duke
 * @version 1.0
 * @Description 用户接口测试类
 * @date 2019/7/18 11:16
 */
public class SysUserServiceTest extends UserApplicationTest {

    private Logger logger = LoggerFactory.getLogger(SysUserServiceTest.class);

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void test() {
        logger.warn(MD5Utils.encrypt("duke"));
    }

    @Test
    public void deleteByPrimaryKey() {
        logger.warn("deleteByPrimaryKey result :" + sysUserService.deleteByPrimaryKey(7));
    }

    @Test
    public void insert() {
        SysUser record = new SysUser();
        record.setUname("duke1");
        record.setPassword("123456");
        record.setName("江南制造总局");
        int result = sysUserService.insert(record);
        logger.info("insert record : " + JSON.toJSONString(record) + ", result: " + result);
    }

    @Test
    public void insertSelective() {
        SysUser record = new SysUser();
        record.setUname("duke2");
        record.setPassword("123456");
        record.setName("江南制造总局");
        int result = sysUserService.insertSelective(record);
        logger.info("insert record : " + JSON.toJSONString(record) + ", result: " + result);
    }

    @Test
    public void selectByPrimaryKey() {
        SysUser record = sysUserService.selectByPrimaryKey(1);
        logger.info("selectByPrimaryKey record : " + JSON.toJSONString(record));
    }

    @Test
    public void updateByPrimaryKeySelective() {
        SysUser record = new SysUser();
        record.setId(6);
        record.setUname("duke-test");
        record.setPassword("123456");
        record.setName("江南制造总局-test");
        int result = sysUserService.updateByPrimaryKeySelective(record);
        logger.info("updateByPrimaryKeySelective record : " + JSON.toJSONString(sysUserService.selectByPrimaryKey(6)) + ", result: " + result);
    }

    @Test
    public void updateByPrimaryKey() {
        SysUser record = new SysUser();
        record.setId(7);
        record.setUname("duke2-test");
        record.setPassword("123456");
        int result = sysUserService.updateByPrimaryKey(record);
        logger.info("updateByPrimaryKey record : " + JSON.toJSONString(sysUserService.selectByPrimaryKey(7)) + ", result: " + result);
    }
}
