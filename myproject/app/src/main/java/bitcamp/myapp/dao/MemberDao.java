package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Member;
import org.apache.ibatis.annotations.Param;

public interface MemberDao {

    Member findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
