package com.jinhaoxun.dubbo.module.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhaoxun.dubbo.mapper.user.UserRoleMapper;
import com.jinhaoxun.dubbo.user.service.UserRoleService;
import com.jinhaoxun.dubbo.pojo.user.UserRole;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 用户角色服务类
 */
@Service
@Component
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    /**
     * @author jinhaoxun
     * @description 获取所有账号列表
     * @return ResponseResult 获取到的账号列表
     */
    @Override
    public Set<String> selectRoleSet(Long userId){
        return userRoleMapper.selectRoleSet(userId);
    }

}
