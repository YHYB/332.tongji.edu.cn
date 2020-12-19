package team.scholarship;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "com.example.coursetest")
@MapperScan("com.example.coursetest.mapper")
public class CoursetestApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CoursetestApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CoursetestApplication.class);
    }
}
