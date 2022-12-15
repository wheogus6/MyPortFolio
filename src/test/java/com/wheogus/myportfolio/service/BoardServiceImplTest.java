package com.wheogus.myportfolio.service;

import com.wheogus.myportfolio.dao.BoardDao;
import com.wheogus.myportfolio.domain.BoardDto;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardServiceImplTest extends TestCase {

    @Autowired
    private BoardDao boardDao;

    @Test
    public void testRemoveAll() throws Exception{
        boardDao.deleteAll();
        for (int i = 0; i < 10; i++) {
            BoardDto boardDto = new BoardDto("title", "writer", "content");
            boardDao.insert(boardDto);
        }
        assertTrue(boardDao.count()==10);
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);
    }

    @Test
    public void testRemove() throws Exception{
        boardDao.deleteAll();
        BoardDto boardDto = new BoardDto("title1", "writer1", "content1");
        BoardDto boardDto2 = new BoardDto("title2", "writer2", "content2");
        boardDao.insert(boardDto);
        boardDao.insert(boardDto2);
        Integer count = boardDao.count();
        System.out.println(count);

        Integer num = boardDao.selectAll().get(0).getNum();
        Integer num2 = boardDao.selectAll().get(1).getNum();
        System.out.println(num);
        System.out.println(num2);

        assertTrue(boardDao.delete(num,boardDto.getWriter())==1);
        assertTrue(boardDao.delete(num2,boardDto2.getWriter())==1);
        assertTrue(boardDao.count()==0);

        Integer count2 = boardDao.count();
        System.out.println(count2);

    }

    @Test
    public void testGetCount() throws Exception {
        boardDao.deleteAll();
        BoardDto boardDto = new BoardDto("title1", "writer1", "content1");
        BoardDto boardDto2 = new BoardDto("title2", "writer2", "content2");
        boardDao.insert(boardDto);
        boardDao.insert(boardDto2);
        assertTrue(boardDao.count()==2);
        Integer count = boardDao.count();
        System.out.println("count = " + count);
    }

    @Test
    public void testInsert() throws Exception{
        boardDao.deleteAll();
        BoardDto boardDto = new BoardDto("title1", "writer1", "content1");
        BoardDto boardDto2 = new BoardDto("title2", "writer2", "content2");
        assertTrue(boardDao.insert(boardDto)==1);
        assertTrue(boardDao.insert(boardDto2)==1);
    }

    @Test
    public void testGetList() throws Exception{
        boardDao.deleteAll();
        BoardDto boardDto1 = new BoardDto("title1", "writer1", "content1");
        BoardDto boardDto2 = new BoardDto("title2", "writer2", "content2");
        boardDao.insert(boardDto1);
        boardDao.insert(boardDto2);
        List<BoardDto> list = boardDao.selectAll();
        System.out.println("list = " + list);
        assertTrue(list.size()==2);
    }

    @Test
    public void testModify() {

    }
}