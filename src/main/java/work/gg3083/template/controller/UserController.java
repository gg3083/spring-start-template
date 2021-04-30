package work.gg3083.template.controller;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import work.gg3083.template.component.JwtHelper;
import work.gg3083.template.entity.User;
import work.gg3083.template.entity.enums.TokenVerifyEnum;
import work.gg3083.template.entity.json.JsonBack;
import work.gg3083.template.entity.param.UserAddParam;
import work.gg3083.template.entity.param.UserUpdateParam;
import work.gg3083.template.entity.vo.PageInfo;
import work.gg3083.template.entity.vo.UserVO;
import work.gg3083.template.exception.MyExceptionType;
import work.gg3083.template.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
@RestController
@RequestMapping("/user")
@Api(value="用户控制器", tags={"用户相关"})
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtHelper jwtHelper;

    @GetMapping("list")
    public JsonBack list(@RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo,
                                         @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize,
                                         String searchKey){

        return JsonBack.buildSuccJson(userService.list4Page(pageNo,pageSize,searchKey));
    }

    @PostMapping("add")
    public JsonBack add(@RequestBody @Validated UserAddParam param){
        return JsonBack.buildSuccJson(userService.add(param));
    }

    @PostMapping("update")
    public JsonBack update(@RequestBody @Validated UserUpdateParam param){
        return JsonBack.buildSuccJson(userService.update(param));
    }

    @GetMapping("/")
    public JsonBack get(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String id = jwtHelper.parseToken(token);
        UserVO userVo = userService.findUserVoByUserId(id);
        return JsonBack.buildSuccJson(userVo);
    }

    @GetMapping("/refreshToken")
    public JsonBack refreshToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        TokenVerifyEnum verifyEnum = jwtHelper.validationToken(token);
        if (verifyEnum == TokenVerifyEnum.EXPIRED){
            String id = jwtHelper.parseToken(token);
            UserVO userVo = userService.findUserVoByUserId(id);
            String newToken = jwtHelper.generateToken(userVo);
            return JsonBack.buildSuccJson(newToken);
        }
        return JsonBack.buildErrorJson(MyExceptionType.TOKEN_FAIL);
    }

    @PostMapping("delete")
    public JsonBack delete(@RequestParam Integer id){
        return JsonBack.buildSuccJson(userService.delete(id));
    }
}

