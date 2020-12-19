package team.scholarship;

import team.scholarship.bean.PlayerBean;
import team.scholarship.service.PlayerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Brian.Z
 * @project chocolateFactory
 * @classname TestApplication
 * @description TODO
 * @date 2020/12/16 16:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication extends SpringBootServletInitializer {

    @Autowired
    PlayerService playerService;

    @Test
    public void contextLoads() {
        PlayerBean playerBean = playerService.searchPlayer("Young");
        System.out.println("该用户为");
        System.out.println(playerBean.getName());
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}

