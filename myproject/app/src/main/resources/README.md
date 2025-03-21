# 01. Servlet/JSP MVC 모델
## 학습목표
- Gradle로 Java 프로젝트 준비
- Maven 표준 프로젝트 구조를 이해
- 빌드 스클립트 파일(build.gradle, settings.gradle) 구조 이해
- 의존 라이브러리 별명 다루기
- 서블릿/JSP 구동 원리 이해
- MVC 모델 이해
- 로그인/로그아웃, 게시판 CRUD

# 02. 서비스, DAO 컴포넌트에 인터페이스 도입
## 학습목표
- SOLID 객체지향 설계 원칙의 이해
    - SRP(Single Responsibility Principle): 한 클래스는 한 역할만 담당한다.
        - 예) 컴포넌트를 역할에 따라 MVC 구조로 나눈 것.
    - OCP(Open/Closed Principle): 기존 코드를 변경하지 않고 기능 확장한다.
        - 예) 리스너, 서블릿, 필터를 추가한 것.
    - LSP(Liskov Substitution Principle): 상위 타입을 하위 타입으로 교체해도 정상 동작한다.
        - 예) Truck 클래스와 그 하위 클래스인 DumpTruck, PickupTruck 클래스
    - ISP(Interface Segregation Principle): 객체를 용도에 맞춰서 제한적으로 바라볼 수 있게 한다.
        - 예) 컬렉션 클래스의 Collection, Iterator 인터페이스
    - DIP(Dependency Inversion Principle): 구체적인 클래스에 의존하기 보다는 추상화에 의존한다.
        - 예) BoardService가 사용하는 의존 객체를 인터페이스로 설정한다.
- GRASP 설계 패턴의 이해

## 작업
### 1. SOLID-DIP(Dependency Inversion Principle)
- 구체적인 구현에 의존하지 말고 인터페이스나 추상 클래스에 의존하라
    - 클래스를 직접 사용하지 말고 인터페이스나 추상클래스를 사용하라!
    - 요구사항 변경에 맞춰 클래스를 교체하기 쉽다.
- BoardDao 클래스 변경
    - BoardDao 인터페이스 생성
    - MySQLBoardDao 클래스 생성
- BoardService 클래스 변경
    - BoardService 인터페이스 생성
    - BoardServiceImpl 또는 DefaultBoardService 클래스 생성
- MemberDao 클래스 변경
    - MemberDao 인터페이스 생성
    - MySQLMemberDao 클래스 생성
- MemberService 클래스 변경
    - MemberService 인터페이스 생성
    - MemberServiceImpl 또는 DefaultMemberService 클래스 생성

### 2. RuntimeException과 예외 처리 커스터마이징
- DaoException 예외 클래스 생성
    - 예외 상황을 좀 더 상세하게 분류하기 위해 서브 클래스를 만든다.
    - 예외 발생 시 어느 클래스에서 예외가 발생했는지 확인하기 쉽다.
    - 상위 호출자에게 전달하기 쉽도록 RuntimeException 예외로 만든다.

# 03. 네이버 클라우드의 Object Storage 활용: 게시글 첨부파일 보관

## 학습목표

- 서블릿 필터를 다룰 수 있다.
- 네이버 클라우드의 Object Storage 사용할 수 있다.
- 멀티파트 파일 업로드를 다룰 수 있다.

## 작업

### 1. SOLID 원칙에서 OCP 적용하기

- CharacterEncodingFilter 클래스 생성
    - 기존 서블릿 클래스를 손대지 않고(Closed) 요청 데이터의 문자 집합을 지정하기(Open)


### 2. 게시글 입력 폼 변경

- /board/form.jsp 변경
    - <form> 태그에 enctype="multipart/form-data" 지정.

### 3. 네이버 클라우드의 Storage Object 서비스 설정

- 버킷 생성(예: bitcamp-118)

### 4. Object Storage 서비스를 활용한 파일 업로드 처리

- 서비스 컴포넌트 생성
    - StorageService 인터페이스 생성
        - upload() 추가
    - NCPObjectStorageService 클래스생성
        - upload() 구현
- BoardAddServlet 변경
    - NCP의 Object Storage를 이용한 파일 업로드 적용

### 5. 첨부파일 정보를 DB에 저장

- ed_attach_file 테이블에 origin_filename 컬럼 추가
- BoardFile 클래스 생성
- BoardFileDao 인터페이스 생성
    - insert() 추가
- DefaultBoardFileDao 클래스 생성
    - insert() 구현
- DefaultBoardService 클래스 변경
    - add() 변경
- ContextLoaderListener 클래스 변경

### 6. 애플리케이션 프로퍼티를 .properties 파일에 저장

- $HOME/config/bitcamp-study.properties 파일 생성
    - NCP Object Storage 관련 정보 보관
    - JDBC 관련 정보 보관
- .properties 파일 로딩
    - ContextLoaderListener 클래스 변경
    - NCPObjectStorageService 클래스 변경

### 7. 게시글 조회 화면에 첨부 파일 목록 출력

- MySQLBoardDao.findByNo() 변경
- /board/detail.jsp 변경

### 8. 첨부파일 다운로드

- BoardFileDao 인터페이스 변경
    - findByNo() 추가
- MySQLBoardFileDao 클래스 변경
    - findByNo() 구현
- BoardService 인터페이스 변경
    - getAttachedFile() 추가
- DefaultBoardService 클래스 변경
    - getAttachedFile() 구현
- StorageService 인터페이스 변경
    - download() 추가
- NCPObjectStorageService 클래스 변경
    - download() 구현
- BoardFileDownloadServlet 생성

### 9. 첨부파일 삭제

- /board/detail.jsp 변경
    - 첨부파일 링크 옆에 삭제 버튼 추가
- BoardFileDao 인터페이스 변경
    - delete() 추가
- MySQLBoardFileDao 클래스 변경
    - delete() 구현
- BoardService 인터페이스 변경
    - deleteAttachedFile() 추가
- DefaultBoardService 클래스 변경
    - deleteAttachedFile() 구현
- StorageService 인터페이스 변경
    - delete() 추가
- NCPObjectStorageService 클래스 변경
    - delete() 구현
- BoardFileDeleteServlet 생성

### 10. 게시글 변경할 때 첨부파일 추가

- /board/detail.jsp 변경
    - 첨부 파일 입력 항목 추가
- BoardUpdateServlet 변경
    - 멀티파트 파일 업로드 처리 추가
- DefaultBoardService 변경
    - update() 변경

### 11. 게시글 조회수 증가

- BoardService 인터페이스 변경
    - increaseViewCount() 추가
- DefaultBoardService 클래스 변경
    - increaseViewCount() 구현
- BoardDao 인터페이스 변경
    - updateViewCount() 추가
- MySQLBoardDao 클래스 변경
    - updateViewCount() 구현
- BoardDetailServlet 클래스 변경
    - 게시글 조회수 증가

## HTTP 프로토콜 POST 요청
### application/x-www-form-urlencoded
POST /board/add HTTP/1.1
Host: 192.168.0.10:8888
Content-Length: 126
Pragma: no-cache
Cache-Control: no-cache
Origin: http://192.168.0.10:8888
Content-Type: application/x-www-form-urlencoded
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7
Referer: http://192.168.0.10:8888/board/form
Accept-Encoding: gzip, deflate
Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7,la;q=0.6,cs;q=0.5
Cookie: JSESSIONID=33220E6042E6755B37E9878D1876CB86
Connection: keep-alive

title=ABC%EA%B0%80%EA%B0%81&content=ABC%EA%B0%80%EA%B0%81%EA%B0%84&files=IMG_1624.jpeg&files=IMG_1627.jpeg&files=IMG_1713.jpeg

### multipart/form-data
POST /board/add HTTP/1.1
Host: 192.168.0.10:8888
Content-Length: 5320188
Pragma: no-cache
Cache-Control: no-cache
Origin: http://192.168.0.10:8888
Content-Type: multipart/form-data; boundary=----WebKitFormBoundaryv4NOk2pPS6PJbYGY
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7
Referer: http://192.168.0.10:8888/board/form
Accept-Encoding: gzip, deflate
Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7,la;q=0.6,cs;q=0.5
Cookie: JSESSIONID=6CAA97882A73E7E2FC404DB084455BD9
Connection: keep-alive

------WebKitFormBoundaryv4NOk2pPS6PJbYGY
Content-Disposition: form-data; name="title"

aaa
------WebKitFormBoundaryv4NOk2pPS6PJbYGY
Content-Disposition: form-data; name="content"

aaaaaa
------WebKitFormBoundaryv4NOk2pPS6PJbYGY
Content-Disposition: form-data; name="files"; filename="IMG_1624.jpeg"
Content-Type: image/jpeg

...
------WebKitFormBoundaryv4NOk2pPS6PJbYGY
Content-Disposition: form-data; name="files"; filename="IMG_1627.jpeg"
Content-Type: image/jpeg

...
------WebKitFormBoundaryv4NOk2pPS6PJbYGY--

# 04. SQL 삽입 공격 차단하기 및 부모 테이블 데이터 삭제하기

## 학습목표

- SQL 삽입 공격의 개념을 이해한다.
- SQL 삽입 공격의 예를 보일 수 있다.
- SQL 삽입 공격의 보안 약점을 해소하는 코드를 작성할 수 있다.
  - PreparedStatement를 사용할 수 있다.
- 자식 테이블에 데이터가 있는 부모 테이블의 데이터를 삭제할 수 있다.


## 작업

### 1. 삽입 공격 방어하기

- MySQLBoardDao 변경
  - Statement를 PreparedStatement로 변경한다.
- MySQLBoardFileDao 변경
  - Statement를 PreparedStatement로 변경한다.
- MySQLMemberDao 변경
  - Statement를 PreparedStatement로 변경한다.

### 2. 첨부 파일이 있는 게시글 삭제 오류 해결

- 게시글의 첨부 파일 데이터 모두 삭제하기
  - BoardFileDao.deleteAllByBoardNo() 메서드 추가
  - MySQLBoardFileDao.deleteAllByBoardNo() 메서드 구현
- 게시글 삭제할 때 첨부 파일 데이터를 먼저 삭제하기
  - DefaultBoardService.delete() 메서드 변경
- 게시글 삭제할 때 네이버 클라우드의 업로드 파일도 삭제하기
  - BoardDeleteServlet 변경

# 05. 트랜잭션 다루기

## 학습목표

- 트랜잭션의 개념을 설명할 수 있다.
- MySQL에서 트랜잭션을 다룰 수 있다.

## 작업

### 1. MySQL 클라이언트를 통해 트랜잭션 제어

- 트랜잭션을 시작하기
  - `mysql> set autocommit = 0`
- 데이터 변경 작업 실행하기
  - `insert into ed_board(member_id, title, content) values(101, 'aaa1', 'aaaaa');`
  - `insert into ed_board(member_id, title, content) values(102, 'aaa1', 'aaaaa');`
  - `insert into ed_board(member_id, title, content) values(103, 'aaa1', 'aaaaa');`
  - `update ed_board set title='xxxx' where board_id=27;`
- 데이터 결과 조회
  - 클라이언트1: select 실행 및 결과 확인
  - 클라이언트2: select 실행 및 결과 확인
  - 결과의 차이점을 이해
- commit 실행 후
  - 클라이언트1과 클라이언트2의 select 실행 결과 확인
- rollback 실행 후
  - 클라이언트1과 클라이언트2의 select 실행 결과 확인

### 2. JDBC 에서 트랜잭션 제어

- DefaultBoardService 변경
  - add(), update(), delete(), increaseViewCount(), deleteAttachedFile() 메서드에 트랜잭션 적용

# 06. 트랜잭션 제어 코드를 캡슐화하기

## 학습목표

- 애노테이션을 정의하고 다룰 수 있다.
- GoF의 Proxy 패턴을 이해하고 구현할 수 있다.

## 작업

### 1. 트랜잭션을 표시할 애노테이션 정의

- Transactional 애노테이션 추가

### 2. 트랜잭션 애노테이션 적용

- DefaultBoardService 변경
  - 트랜잭션을 다루는 메서드에 Transactional 애노테이션을 붙인다.

### 3. 프록시 객체 팩토리 준비

- TransactionProxyFactory 클래스 생성

### 4. 트랜잭션 코드를 처리할 대행자 준비

- TransactionInvocationHandler 클래스 생성

### 5. 서비스 객체에 적용

- ContextLoaderList 클래스 변경

# 07. Mybatis 퍼시스턴스 프레임워크 적용하기

## 학습목표

- Mybatis 퍼시스턴스 프레임워크의 구동 원리를 이해하고 적용할 수 있다. .

## 작업

### 1. Mybatis 라이브러리 설치

- build.gradle 변경
  - 'org.mybatis:mybatis:3.5.19' 추가
  - 'org.apache.commons:commons-dbcp2:2.13.0' 추가

### 2. Mybatis 설정 및 객체 준비

- jdbc.properties 생성
  - DB 접속 정보 설정
- mybatis-config.xml 생성
  - Mybatis 설정
- ContextLoaderListener 변경
  - Mybatis 관련 객체 준비

### 3. Mybatis 적용

`Connection`을 직접 사용하는 대신에 Mybatis의 `SqlSession`을 사용하여 SQL 실행

- MySQLBoardDao 변경
  - SQL문을 SQL 매퍼 파일로 옮긴다.
  - Mybatis 코드를 적용한다.
- MySQLBoardFileDao 변경
  - SQL문을 SQL 매퍼 파일로 옮긴다.
  - Mybatis 코드를 적용한다.
- MySQLMemberDao 변경
  - SQL문을 SQL 매퍼 파일로 옮긴다.
  - Mybatis 코드를 적용한다.
- bitcamp/myapp/mapper/BoardDao.xml 생성
  - MySQLBoardDao에서 사용할 SQL 문을 보관
- bitcamp/myapp/mapper/BoardFileDao.xml 생성
  - MySQLBoardFileDao에서 사용할 SQL 문을 보관
- bitcamp/myapp/mapper/MemberDao.xml 생성
  - MySQLMemberDao에서 사용할 SQL 문을 보관

### 4. 트랜잭션 제어

- TransactionInvocationHandler 변경
- TransactionProxyFactory 변경
- SqlSessionFactoryProxy 생성
  - 같은 스레드가 같은 SqlSession을 사용하도록 openSession()을 구현한다.
- SqlSessionProxy 생성
  - close()를 호출할 때 자원해제 하지 않도록 변경한다.
  - realClose()를 호출할 때 자원을 해제하도록 한다.
- ContextLoaderListener 변경

### 5. XML 설정 대신 자바 코드로 설정하기

- ContextLoaderListener 변경

# 08. Front Controller 패턴 적용하기 I - DispatcherServlet 만들기

## 학습목표

- FrontController 패턴의 구조와 동작 원리를 이해하고 구현할 수 있다.
- GoF의 Facade 패턴을 이해한다.

## 작업

### 1. Front Controller 역할을 할 서블릿 생성

- DispatcherServlet 클래스 생성

### 2. 기존 서블릿을 Page Controller로 전환

- XxxServlet 클래스 변경
- *.jsp 변경

# 09. Front Controller 패턴 적용하기 II - 페이지 컨트롤러를 POJO로 바꾸기

## 학습목표

- POJO의 의미를 이해하고 구현할 수 있다.
- Reflection API를 사용하여 특정 애노테이션이 붙은 메서드를 찾아 호출할 수 있다.
- Generic을 사용할 수 있다.

## 작업

### 1. 페이지 컨트롤러 역할을 수행하는 서블릿을 POJO로 변환

- XxxServlet ==> XxxController 로 이름 변경
  - POJO 클래스로 변환
- ContextLoaderListener 변경
  - POJO로 변환한 페이지 컨트롤러를 ServletContext에 보관한다.
- DispatcherServlet 변경
  - 클라이언트 요청이 들어오면 그 요청을 처리할 객체를 ServletContext에서 꺼내 실행한다.

### 2. 클라이언트 요청을 처리할 메서드에 붙일 애노테이션 정의

- RequestMapping 애노테이션 생성

### 3. 클라이언트 요청을 처리할 메서드에 애노테이션을 붙인다.

- XxxController 변경
  - 요청을 처리할 메서드에 애노테이션을 붙인다.
- DispatcherServlet 변경
  - ServletContext에서 꺼낸 객체에서 애노테이션이 붙은 메서드를 찾아 호출한다.

### 4. 관련된 메서드를 한 클래스에 통합

- AuthController 클래스 생성
  - LoginFormController
  - LoginController
  - LogoutController
- BoardController 클래스 생성
  - BoardListController
  - BoardDetailController
  - BoardFormController
  - BoardAddController
  - BoardUpdateController
  - BoardDeleteController
  - BoardFileDownloadController
  - BoardFileDeleteController