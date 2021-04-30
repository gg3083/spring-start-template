package work.gg3083.template.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
public class AccessDeniedAuthHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        log.debug("auth:miss");
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type,Authorization");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        httpServletResponse.getWriter().write("{\"perm\":\"miss\"}");
    }
}
