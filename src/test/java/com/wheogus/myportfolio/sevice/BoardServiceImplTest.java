package com.wheogus.myportfolio.sevice;

import com.wheogus.myportfolio.dao.BoardDao;
import com.wheogus.myportfolio.domain.BoardDto;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardServiceImplTest extends TestCase {

    @Autowired
    private BoardDao boardDao;

    @Test
    public void testRemoveAll() throws Exception{
        boardDao.deleteAll();
        for (int i = 1; i < 10; i++) {
            BoardDto boardDto = new BoardDto("title", "writer", "content");
            boardDao.insert(boardDto);
        }
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);
    }

    @Test
    public void testRemove() {
    }

    @Test
    public void testGetCount() {
    }

    @Test
    public void testInsert() {
    }

    @Test
    public void testGetList() {
    }

    @Test
    public void testModify() {
    }
}