package com.jinhaoxun.dubbo.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinhaoxun.dubbo.pojo.user.UserRole;

import java.util.Set;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 用户角色Mapper接口
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * @author jinhaoxun
     * @description 获得用户角色列表
     * @param userId 用户id
     * @return ResponseResult 用户角色集合
     */
    Set<String> selectRoleSet(Long userId);

}
