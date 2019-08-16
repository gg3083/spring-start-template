package work.gg3083.template.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import work.gg3083.template.QuickStartApplicationTests;

/**
 * @author Gimi
 * @date 2019/8/16 10:13
 */
public class UserServiceTest extends QuickStartApplicationTests {

    @Autowired
    IUserService userService;

    @Test
    public void testLogin(){
        System.err.println(userService.findUserVoByLoginName("admin"));
    }
}
