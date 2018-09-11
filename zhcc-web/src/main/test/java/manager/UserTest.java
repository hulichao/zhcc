package zhcc.com.hu.zhcc.shiro.manager;

import com.hu.zhcc.common.entity.SysUserEntity;
import com.hu.zhcc.common.utils.AESUtil;
import com.hu.zhcc.common.utils.MD5Utils;
import com.hu.zhcc.shiro.manager.SysUserManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by hulichao on 2018/7/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml"})
public class UserTest {

    @Autowired
    private SysUserManager sysUserManager;
    @Test
    public void testInsetUser(){
        SysUserEntity sysUserEntity = sysUserManager.getById(1L);

        sysUserEntity.setPassword("123456");
        System.out.println(sysUserEntity.getPassword());
        sysUserManager.updatePswd(sysUserEntity);
        System.out.println(sysUserEntity.getPassword());
    }

    @Test
    public void getUser(){
        SysUserEntity sysUserEntity = sysUserManager.getById(1L);
        System.out.println(sysUserEntity.getPassword());
    }
}
