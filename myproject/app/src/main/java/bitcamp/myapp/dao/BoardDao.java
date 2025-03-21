package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Board;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoardDao {

    List<Board> findAll();

    int insert(Board board);

    Board findByNo(int no);

    int update(Board board);

    int delete(int no);

    int updateViewCount(@Param("no") int no, @Param("increment") int increment);
}
