package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.pojo.Users;
import com.example.service.UsersService;
import com.example.mapper.UsersMapper;
import org.springframework.stereotype.Service;

/**
* @author 11963
* @description 针对表【users】的数据库操作Service实现
* @createDate 2024-06-20 10:48:21
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{

}




