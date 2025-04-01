package bitcamp.myapp;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
@PropertySource("file:${user.home}/Desktop/bitcamp-study.properties")

// @Transactional 애노테이션 활성화
//@EnableTransactionManagement

// DAO 구현체 자동 생성을 설정하는 방법 1) 애노테이션으로 설정하기
//@MapperScan(basePackages = "bitcamp.myapp.dao")
public class App {

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    // 스프링부트의 프로퍼티 값(application.properties)을 커스터마이징 할 때 사용하는 객체
    private final ConfigurableEnvironment configurableEnvironment;

    public App(ConfigurableEnvironment configurableEnvironment) {
        this.configurableEnvironment = configurableEnvironment;
    }

    @PostConstruct  // 생성자 호출 후에 스프링 IoC 컨테이너가 호출하는 메서드
    public void init() throws Exception {
        HashMap<String, Object> datasourceProperties = new HashMap<>();
        datasourceProperties.put("spring.datasource.driver-class-name", driver);
        datasourceProperties.put("spring.datasource.url", url);
        datasourceProperties.put("spring.datasource.username", username);
        datasourceProperties.put("spring.datasource.password", password);

        MapPropertySource propertySource = new MapPropertySource("dynamicProperties", datasourceProperties);
        configurableEnvironment.getPropertySources().addFirst(propertySource);
    }

    public static void main(String[] args) {
        System.out.println("App 실행");
        SpringApplication.run(App.class, args);
    }
}