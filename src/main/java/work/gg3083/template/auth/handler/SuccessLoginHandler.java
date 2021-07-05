package work.gg3083.template.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import work.gg3083.template.cache.ICache;
import work.gg3083.template.commom.CommonConst;
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

    @Autowired
    private ICache cache;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("auth pass");

        UserVO userVo = userService.findUserVoByLoginName(authentication.getName());
        HashMap<String, Object> map = new HashMap<>();
        map.put(CommonConst.ID,userVo.getId());
        map.put(CommonConst.LOGIN_NAME,userVo.getLoginName());
        map.put(CommonConst.ROLE_ID,userVo.getRoleId());
        String token = helper.createToken(map);
        userVo.setToken(token);
        cache.put(String.valueOf(userVo.getId()), userVo.getPermList());
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type,Authorization");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        httpServletResponse.getWriter().write(JsonUtil.beanToJson(JsonBack.buildSuccJson(userVo, "登录成功")));
    }
}
