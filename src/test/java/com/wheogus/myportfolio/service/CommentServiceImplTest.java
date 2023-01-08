package com.wheogus.myportfolio.service;

import com.wheogus.myportfolio.dao.BoardDao;
import com.wheogus.myportfolio.dao.CommentDao;
import com.wheogus.myportfolio.domain.CommentDto;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CommentServiceImplTest extends TestCase {

    @Autowired
    CommentDao commentDao;
    @Autowired
    BoardDao boardDao;

//    @Test
//    public void testCount() throws Exception {
//        commentDao.deleteAll();
//        CommentDto commentDto = new CommentDto(1, 1, "wheogus", "wheogus");
//        commentDao.insert(commentDto);
//        Integer num = commentDto.getNum();
//        System.out.println("num = " + num);
//        assertTrue(commentDao.count(num) == 1);
//
//
//    }

//    public void testGetList() {
//    }
//
//    public void testRead() {
//    }
//
//    public void testDelete() {
//    }

    @Test
    public void testWrite() throws Exception{
        commentDao.deleteAll();
        Integer num = boardDao.selectAll().get(0).getNum();
        CommentDto commentDto = new CommentDto(1, num, "wheogus", "wheogus");
        System.out.println("commentDto = " + commentDto);
        assertTrue(commentDao.insert(commentDto)==1);
    }

//    public void testModify() {
//    }
}