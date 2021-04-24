package work.gg3083.template.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.gg3083.template.entity.json.JsonBack;
import work.gg3083.template.service.IUserService;

/**
 * @author Gimi
 * @date 2019/8/16 14:48
 */
@RestController
@RequestMapping("/user")
@Api(value="登录", tags={"用户相关"})
public class LoginController {

    @Autowired
    private IUserService userService;

    @PostMapping("login")
    @ApiOperation("登录")
    @ApiImplicitParams ({
            @ApiImplicitParam(paramType="query", name = "username", value = "账号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "password", value = "密码", required = true, dataType = "String")
    })
    public JsonBack login(String username, String password){
        return JsonBack.buildSuccJson(new Object());
    }

    @PostMapping("register")
    @ApiOperation("注册")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "loginName", value = "账号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "password", value = "密码", required = true, dataType = "String")
    })
    public JsonBack register(String loginName,String password){
        userService.register( loginName , password );
        return JsonBack.buildSuccJson();
    }


    @PostMapping("/logout")
    @ApiOperation("退出登录")
    public JsonBack logout(){
        return JsonBack.buildSuccJson();
    }
}
