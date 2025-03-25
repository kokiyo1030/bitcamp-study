package bitcamp.myapp;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@SpringBootApplication
@PropertySource("file:${user.home}/Desktop/bitcamp-study.properties")

// @Transactional 애노테이션 활성화
//@EnableTransactionManagement

// DAO 구현체 자동 생성을 설정하는 방법 1) 애노테이션으로 설정하기
@MapperScan(basePackages = "bitcamp.myapp.dao")
public class App {

/*
    // DAO 구현체 자동 생성을 설정하는 방법 2) 자바 코드로 설정하기
    // - 다음 메서드를 통해 DAO 인터페이스의 구현체를 자동으로 생성해주는 일을 하는 객체를 준비한다.
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("bitcamp.myapp.dao"); // DAO 인터페이스 패키지 설정
        // 단, SQL 매퍼파일(*Dao.xml)의 namespace가 인터페이스의 full-qualified name과 일치해야한다
        // 그리고 SQL id와 인터페이스의 메서드 이름과 일치해야한다
        // 메서드의 파라미터 값이 2개 이상일 경우 @Param 애노테이션으로 프로퍼티 이름을 명시해야한다
        return configurer;
    }
 */

    public static void main(String[] args) {
        System.out.println("App 실행");
        SpringApplication.run(App.class, args);
    }

}