package bitcamp.myapp.member;

public interface MemberService {
    Member get(String email);
    int changePassword(String email, String password);
}
