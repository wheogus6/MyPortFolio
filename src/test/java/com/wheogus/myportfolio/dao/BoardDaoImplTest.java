package com.wheogus.myportfolio.dao;

import junit.framework.TestCase;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardDaoImplTest extends TestCase {

    @Autowired
    private BoardDao boardDao;

    @Test
    public void testDeleteAll() throws Exception{
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);
    }

    public void testDelete() {
    }

    public void testCount() {
    }

    public void testSelectAll() {
    }

    public void testInsert() {
    }

    public void testUpdate() {
    }
}