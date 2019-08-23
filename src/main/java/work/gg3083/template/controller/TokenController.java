package work.gg3083.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.gg3083.template.commom.Const;
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
public class TokenController {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private IUserService userService;

    @GetMapping("get")
    public JsonBack getToken(String loginName){
        HashMap map = new HashMap();
        UserVO userVo = userService.findUserVoByLoginName(loginName);
        if (userVo == null )return JsonBack.buildErrorJson("账户名不存在");
        map.put(Const.ID,userVo.getId());
        map.put(Const.LOGIN_NAME,userVo.getLoginName());
        map.put(Const.ROLE_ALIAS,userVo.getRoleAlias());
        String token = jwtHelper.createToken(map);
        return JsonBack.buildSuccJson(token);
    }
}
