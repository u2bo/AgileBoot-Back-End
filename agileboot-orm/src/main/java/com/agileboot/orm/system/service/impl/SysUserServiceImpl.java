package com.agileboot.orm.system.service.impl;

import com.agileboot.orm.system.entity.SysPostEntity;
import com.agileboot.orm.system.entity.SysRoleEntity;
import com.agileboot.orm.system.entity.SysUserEntity;
import com.agileboot.orm.system.mapper.SysUserMapper;
import com.agileboot.orm.common.query.AbstractPageQuery;
import com.agileboot.orm.system.result.SearchUserDO;
import com.agileboot.orm.system.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements ISysUserService {


    @Override
    public boolean isUserNameDuplicated(String username) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return this.baseMapper.exists(queryWrapper);
    }


    @Override
    public boolean isPhoneDuplicated(String phone, Long userId) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne(userId != null, "user_id", userId)
            .eq("phone_number", phone);
        return baseMapper.exists(queryWrapper);
    }


    @Override
    public boolean isEmailDuplicated(String email, Long userId) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne(userId != null, "user_id", userId)
            .eq("email", email);
        return baseMapper.exists(queryWrapper);
    }


    @Override
    public SysRoleEntity getRoleOfUser(Long userId) {
        List<SysRoleEntity> list = baseMapper.getRolesByUserId(userId);
        return list.isEmpty() ? null : list.get(0);
    }


    @Override
    public SysPostEntity getPostOfUser(Long userId) {
        List<SysPostEntity> list = baseMapper.getPostsByUserId(userId);
        return list.isEmpty() ? null : list.get(0);
    }


    @Override
    public Set<String> getMenuPermissionsForUser(Long userId) {
        return baseMapper.getMenuPermsByUserId(userId);
    }


    @Override
    public SysUserEntity getUserByUserName(String userName) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userName);
        return this.getOne(queryWrapper);
    }


    @SuppressWarnings("unchecked")
    @Override
    public Page<SysUserEntity> getUserListByRole(AbstractPageQuery query) {
        return baseMapper.getUserListByRole(query.toPage(), query.toQueryWrapper());
    }


    @SuppressWarnings("unchecked")
    @Override
    public Page<SearchUserDO> getUserList(AbstractPageQuery query) {
        return baseMapper.getUserList(query.toPage(), query.toQueryWrapper());
    }

}
