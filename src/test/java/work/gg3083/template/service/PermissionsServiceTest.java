package work.gg3083.template.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import work.gg3083.template.QuickStartApplicationTests;
import work.gg3083.template.entity.Permissions;
import work.gg3083.template.entity.vo.PageInfo;
import work.gg3083.template.mapper.PermissionsMapper;

import java.util.List;

/**
 * @author Gimi
 * @date 2019/8/16 9:58
 */
public class PermissionsServiceTest extends QuickStartApplicationTests {

    @Autowired
    IPermissionsService permissionsService;

    @Autowired
    PermissionsMapper permissionsMapper;

    Integer pageNo = 2;
    Integer pageSize = 2;
    String searchKey = "";

    @Test
    public void list4page(){
        PageInfo<Permissions> page = permissionsService.list( pageNo,pageSize,searchKey);
        System.err.println(page);
        System.err.println("-------------------");
        page.getData().forEach( s -> System.err.println(s));

    }
    @Test
    public void findPermByLoginName(){
        List<Permissions> page = permissionsService.findPermByLoginName(searchKey);
        System.err.println( page );
    }
}
