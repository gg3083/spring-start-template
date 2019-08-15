package work.gg3083.template.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

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
public class SuccessLogoutHandler implements LogoutSuccessHandler {


    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.debug("logout:success");
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write("{\"auth\":\"success\"}");
    }
}
