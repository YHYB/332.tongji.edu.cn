package team.scholarship;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "team.scholarship")
@MapperScan("team.scholarship.mapper")
public class ScholarshipSystemApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ScholarshipSystemApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ScholarshipSystemApplication.class);
    }
}
