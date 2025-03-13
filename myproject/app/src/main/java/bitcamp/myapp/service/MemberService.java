package bitcamp.myapp.service;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;

public interface MemberService {

    public Member get(String email, String password);
}
