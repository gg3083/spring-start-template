package work.gg3083.template.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.gg3083.template.commom.CommonConst;
import work.gg3083.template.component.JwtHelper;
import work.gg3083.template.entity.json.JsonBack;
import work.gg3083.template.entity.vo.UserVO;
import work.gg3083.template.service.IUserService;

import java.util.HashMap;

/**
 * @author Gimi
 * @date 2019/8/15 16:32
 */
@RestController
@RequestMapping("/token")
@Api(value="Token控制器", tags={"用户相关"})
public class TokenController {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private IUserService userService;

    @GetMapping("get")
    public JsonBack getToken(String loginName){
        HashMap<String, Object> map = new HashMap();
        UserVO userVo = userService.findUserVoByLoginName(loginName);
        if (userVo == null )return JsonBack.buildErrorJson("账户名不存在");
        map.put(CommonConst.ID,userVo.getId());
        map.put(CommonConst.LOGIN_NAME,userVo.getLoginName());
        map.put(CommonConst.ROLE_ALIAS,userVo.getRoleAlias());
        String token = jwtHelper.createToken(map);
        return JsonBack.buildSuccJson(token);
    }
}
