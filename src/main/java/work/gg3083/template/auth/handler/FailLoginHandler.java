package work.gg3083.template.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import work.gg3083.template.entity.json.JsonBack;
import work.gg3083.template.util.json.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 *
 * @author Gimi
 * @date 2019/8/13 21:36
 *
 ***/
@Component
@Slf4j
public class FailLoginHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("auth-error");
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JsonUtil.beanToJson(JsonBack.buildErrorJson("登录失败")));
    }
}
