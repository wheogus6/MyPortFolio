package com.wheogus.myportfolio.dao;

import com.wheogus.myportfolio.domain.UserDto;

import java.util.List;

public interface UserDao {
    int deleteAllUser();

    int deleteUser(Integer id);

    int countUser();

    List<UserDto> selectAllUser();

    int insertUser(UserDto userDto);

    int updateUser(UserDto userDto);
}
