package com.wheogus.myportfolio.dao;

import com.wheogus.myportfolio.domain.BoardDto;
import com.wheogus.myportfolio.domain.UserDto;

import java.util.List;

public interface UserDao {
    int deleteAllUser();

    int deleteUser(Integer id);

    int countUser();

    UserDto selectUser(String id);

    int insertUser(UserDto userDto);

    int updateUser(UserDto userDto);
}
