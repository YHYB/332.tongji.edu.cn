package team.scholarship;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @ClassName SecurityConfigSEVEN
 * @Description 项目配置类，允许外部访问
 * @Author Brian.Z
 * @Date 2020/12/20 1:36
 */
@Configuration
@EnableWebSecurity
public class SecurityConfigSEVEN extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //super.configure(http);

        //配置不需要登陆验证
        http.authorizeRequests().anyRequest().permitAll().and().logout().permitAll();
    }

}