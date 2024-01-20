package com.yangsx95.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangsx95.mybatisplus.entity.UserEntity;
import com.yangsx95.mybatisplus.mapper.UserMapper;
import com.yangsx95.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author 杨顺翔
 * @since 2022/07/10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
}
