package work.gg3083.template.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import work.gg3083.template.commom.Const;
import work.gg3083.template.component.JwtHelper;
import work.gg3083.template.entity.json.JsonBack;
import work.gg3083.template.entity.vo.UserVO;
import work.gg3083.template.service.IUserService;
import work.gg3083.template.util.json.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/***
 *
 * @author Gimi
 * @date 2019/8/13 21:36
 *
 ***/
@Component
@Slf4j
public class SuccessLoginHandler implements AuthenticationSuccessHandler {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtHelper helper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("auth-pass");

        UserVO userVo = userService.findUserVoByLoginName(authentication.getName());
        HashMap map = new HashMap();
        map.put(Const.ID,userVo.getId());
        map.put(Const.LOGIN_NAME,userVo.getLoginName());
        map.put(Const.ROLE_ALIAS,userVo.getRoleAlias());
        String token = helper.createToken(map);
        userVo.setToken(token);
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JsonUtil.beanToJson(JsonBack.buildSuccJson("登录成功",userVo)));
    }
}
