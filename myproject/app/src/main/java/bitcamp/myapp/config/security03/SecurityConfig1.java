//package bitcamp.myapp.config.security03;
//
//import bitcamp.myapp.member.Member;
//import bitcamp.myapp.member.MemberService;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
////@Configuration
//public class SecurityConfig1 {
//
//    private static final Log log = LogFactory.getLog(SecurityConfig1.class);
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        log.debug("SecurityFilterChain 준비!");
//        return http
//                // 1) 요청 URL의 접근권한 설정
//                .authorizeHttpRequests()
//                .mvcMatchers("/home", "/css/**").permitAll() // "/home" 과 "/css/**" 는 인증을 검사하지 않는다.
//                .anyRequest().authenticated() // 나머지 요청 URL은 인증을 검사한다.
//                .and() // 접근제어권한설정 등록기를 만든 HttpSecurity 객체를 리턴한다.
//                // 2) 인가되지 않은 요청인 경우 Spring Security 기본 로그인 화면으로 보내기
//                .formLogin(Customizer.withDefaults())
//                // SecurityFilterChain 준비
//                .build();
//    }
//
//    // 사용자 인증을 수행할 객체를 준비
//    @Bean
//    public UserDetailsService userDetailsService(MemberService memberService, PasswordEncoder passwordEncoder) {
//        log.debug("DBUserDetailsService 준비!");
//
//        // DB에 저장된 모든 암호를 BcryptPasswordEncoder를 사용해서 암호화한다
////        memberService.changeAllPassword(passwordEncoder.encode("1111"));
//
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
//                Member member = memberService.get(username);
//                return User.builder()
//                        .username(member.getEmail())
//                        .password(member.getPassword())
//                        .roles("USER")
//                        .build();
//            }
//        };
//    }
//
//    // Spring Security에 기본 장착된 PasswordEncoder를 우리가 만든 인코더로 바꾼다.
//    // Spring Security에서 사용자가 입력한 로그인 정보 중에 암호를 비교할 때,
//    // 이 메서드가 리턴한 PasswordEncoder를 사용하여 비교한다.
//    // 즉 SimplePasswordEncoder.matches() 메서드를 호출하여 비교한다.
//    // 리턴 값이 true이면 로그인 성공이다.
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        log.debug("PasswordEncoder 준비!");
//
//        // Spring에서 제공하는 PasswordEncoder 사용하기
//        return new BCryptPasswordEncoder() {
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                System.out.printf("사용자 입력 암호: %s\n", rawPassword);
//                System.out.printf("encode(사용자 입력 암호): %s\n", encode(rawPassword));
//                System.out.printf("저장된 암호: %s\n", encodedPassword);
//                return super.matches(rawPassword, encodedPassword);
//            }
//        };
//    }
//}
