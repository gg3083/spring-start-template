package work.gg3083.template.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import work.gg3083.template.entity.json.JsonBack;
import work.gg3083.template.util.json.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Gimi
 * @date 2019/6/8 12:20
 */
@Slf4j
public class MyExceptionResolver implements HandlerExceptionResolver {

    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {

        log.error("操作异常", ex);

        ModelAndView mv = new ModelAndView("404");
        try {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            PostMapping postMapping = handlerMethod.getMethod().getAnnotation(PostMapping.class);
            GetMapping getMapping = handlerMethod.getMethod().getAnnotation(GetMapping.class);
            response.setStatus(HttpStatus.OK.value()); //设置状态码
            response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
            response.setCharacterEncoding("UTF-8"); //避免乱码
            response.setHeader("Cache-Control", "no-cache, must-revalidate");
            if (getMapping != null || postMapping != null) {
                response.getWriter().println(JsonUtil.beanToJson(JsonBack.buildErrorJson(ex.getMessage())));
            } else {
                response.getWriter().println(JsonUtil.beanToJson(JsonBack.buildErrorJson(ex.toString().replaceAll("\n", "<br/>"))));
            }
        } catch (Exception e) {
            log.error("操作异常", ex);
        }
        return mv;
    }

}
