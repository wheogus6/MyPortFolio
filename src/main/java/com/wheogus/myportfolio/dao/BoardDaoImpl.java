package com.wheogus.myportfolio.dao;

import com.wheogus.myportfolio.domain.BoardDto;
import com.wheogus.myportfolio.domain.SearchCondition;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardDaoImpl implements BoardDao {

    @Autowired
    private SqlSession session;

    private static String namespace = "com.wheogus.myportfolio.dao.BoardMapper.";


    @Override
    public int deleteAll() throws Exception {
        return session.delete(namespace + "deleteAll");
    }

    @Override
    public int delete(Integer num, String writer) throws Exception {
        Map map = new HashMap();
        map.put("num", num);
        map.put("writer", writer);
        return session.delete(namespace + "delete", map);   //입력값을 받기위해 id 뒤에 반드시 map 입력, 얘는 insert와 다르게 생성자가 없어 map으로 입력받는다.
    }

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }

    @Override
    public List<BoardDto> selectAll() throws Exception {
        return session.selectList(namespace + "selectAll");
    }

    @Override
    public int insert(BoardDto dto) throws Exception {
        return session.insert(namespace + "insert", dto);  //매개변수 dto를 반드시 입력 해야함 , 그래야 dto값을 넘겨줄 수 있다.
    }

    @Override
    public int update(BoardDto dto) throws Exception {
        return session.update(namespace + "update", dto);
    }

    @Override
    public List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception {
        return session.selectList(namespace + "searchSelectPage", sc);
    }
    @Override
    public int searchResultCnt(SearchCondition sc) throws Exception{
        return session.selectOne(namespace + "searchResultCnt", sc);
    }

    @Override
    public BoardDto select(Integer num) throws Exception{
        return session.selectOne(namespace + "select", num);
    }

    @Override
    public List<BoardDto> selectPage(Map map) throws Exception{
        return session.selectList(namespace + "selectPage", map);
    }

    @Override
    public int increaseViewCnt(Integer num) throws Exception{
        return session.update(namespace + "increaseViewCnt", num);
        //데이터 베이스에서 view_cnt 디폴트 값을 0으로 설정 해야 조회수 오름
    }

    @Override
    public int updateCommentCnt(Integer num, int cnt) {
        Map map = new HashMap();
        map.put("cnt", cnt);
        map.put("num", num);
        return session.update(namespace + "updateCommentCnt", map);
    }
}
