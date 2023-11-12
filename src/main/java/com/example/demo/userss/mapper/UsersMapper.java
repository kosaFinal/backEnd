package com.example.demo.userss.mapper;

import com.example.demo.userss.dto.UsersDto;
import com.example.demo.userss.entity.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UsersMapper {

    Users getOneUsers(String userName);

    void signupUsers(Users user);

}
