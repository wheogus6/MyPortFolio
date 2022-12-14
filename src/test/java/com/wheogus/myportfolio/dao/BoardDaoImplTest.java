package com.wheogus.myportfolio.dao;

import com.wheogus.myportfolio.domain.BoardDto;
import junit.framework.TestCase;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardDaoImplTest extends TestCase {

    @Autowired
    private BoardDao boardDao;

    @Test
    public void testDeleteAll() throws Exception{
        System.out.println("boardDao = " + boardDao);
        assertTrue(boardDao != null);

        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);

        BoardDto boardDto = new BoardDto("title", "writer", "content");
        System.out.println("boardDto = " + boardDto);
        assertTrue(boardDao.insert(boardDto)==1);
        assertTrue(boardDao.deleteAll()==1);
        assertTrue(boardDao.count()==0);
    }

    @Test
    public void testDelete() throws Exception{
        boardDao.deleteAll();
        BoardDto boardDto = new BoardDto("new title", "new writer", "new content");
        assertTrue(boardDao.insert(boardDto)==1);
        System.out.println("boardDto = " + boardDto);

        Integer num = boardDao.selectAll().get(0).getNum(); // db num 의 가장 첫번째 가져오기
        assertTrue(boardDao.delete(num, boardDto.getWriter())==1);
        assertTrue(boardDao.count()==0);
    }

    @Test
    public void testCount() throws Exception {
        boardDao.deleteAll();
        BoardDto boardDto = new BoardDto("title1", "writer1", "content1");
        BoardDto boardDto2 = new BoardDto("title2", "writer2", "content2");
        assertTrue(boardDao.insert(boardDto)==1);
        assertTrue(boardDao.insert(boardDto2)==1);

        assertTrue(boardDao.count()==2);
    }

    @Test
    public void testSelectAll() throws Exception {
        boardDao.deleteAll();
        List<BoardDto> list = boardDao.selectAll();
        assertTrue(list.size()==0);
       BoardDto boardDto = new BoardDto("title1", "writer1", "content1");
        BoardDto boardDto2 = new BoardDto("title2", "writer2", "content2");
        assertTrue(boardDao.insert(boardDto)==1);
        assertTrue(boardDao.insert(boardDto2)==1);

        list = boardDao.selectAll();
        assertTrue(list.size()==2);
        System.out.println("list = " + list);

    }
    @Test
    public void testInsert() throws Exception{
        boardDao.deleteAll();
        BoardDto boardDto = new BoardDto("title1", "writer1", "content1");
        assertTrue(boardDao.insert(boardDto)==1);
        assertTrue(boardDao.count()==1);
        System.out.println("boardDto = " + boardDto);
    }
    @Test
    public void testUpdate() throws Exception{
        boardDao.deleteAll();
        BoardDto boardDto = new BoardDto("title1", "writer1", "content1");
        BoardDto boardDto2 = new BoardDto("title2", "writer2", "content2");
        boardDao.insert(boardDto);
        boardDao.insert(boardDto2);

        Integer num = boardDao.selectAll().get(0).getNum();
        Integer num2 = boardDao.selectAll().get(1).getNum();
        System.out.println("num = " + num);
        System.out.println("num2 = " + num2);

        boardDto.setNum(num);
        boardDto2.setNum(num2);
        boardDto.setTitle("C title");
        boardDto.setContent("C content");

        assertTrue(boardDao.update(boardDto)==1);
        System.out.println("boardDto = " + boardDto);
        System.out.println("boardDto2 = " + boardDto2);
    }
}