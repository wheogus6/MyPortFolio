package com.wheogus.myportfolio.dao;

import com.wheogus.myportfolio.dao.UserDao;
import com.wheogus.myportfolio.domain.UserDto;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserDaoImplTest extends TestCase {

    @Autowired
    private UserDao userDao;
    @Test
    public void testDeleteUser() {
    }
    @Test
    public void testInsertUser() {
        userDao.deleteAllUser();
        UserDto userDto = new UserDto("aaaa", "1234", "wheo", "www@wwww.www",null , "kakao");
        System.out.println("userDto = " + userDto);
        assertTrue(userDao.insertUser(userDto)==1);
    }
    @Test
    public void testUpdateUser() {
    }
}