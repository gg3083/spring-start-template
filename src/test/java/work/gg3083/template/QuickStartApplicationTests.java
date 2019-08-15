package work.gg3083.template;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import work.gg3083.template.controller.TokenController;
import work.gg3083.template.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuickStartApplicationTests {


    @Autowired
    IUserService userService;

    @Autowired
    TokenController tokenController;

    @Test
    public void contextLoads() {
        System.err.println(tokenController.getToken("admin"));
    }

}
