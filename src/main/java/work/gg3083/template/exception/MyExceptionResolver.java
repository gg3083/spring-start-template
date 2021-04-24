package work.gg3083.template.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@Component
public class MyExceptionResolver implements HandlerExceptionResolver {

    private static Logger logger = LoggerFactory.getLogger(MyExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {

        logger.error("操作异常", ex);

        ModelAndView mv = new ModelAndView();
        try {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            GetMapping getMapping = handlerMethod.getMethod().getAnnotation(GetMapping.class);
            PostMapping postMapping = handlerMethod.getMethod().getAnnotation(PostMapping.class);
            PutMapping putMapping = handlerMethod.getMethod().getAnnotation(PutMapping.class);
            DeleteMapping deleteMapping = handlerMethod.getMethod().getAnnotation(DeleteMapping.class);
            //设置状态码
            response.setStatus(HttpStatus.OK.value());
            //设置ContentType
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            //避免乱码
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Cache-Control", "no-cache, must-revalidate");
            if (getMapping != null || postMapping != null || putMapping != null || deleteMapping != null) {
                if (ex instanceof DuplicateKeyException){
                    response.getWriter().println(JsonUtil.beanToJson(JsonBack.buildErrorJson(ex.getCause().getMessage())));
                }else {
                    response.getWriter().println(JsonUtil.beanToJson(JsonBack.buildErrorJson( ex.getMessage())));
                }
            } else {
                response.getWriter().println(JsonUtil.beanToJson(JsonBack.buildErrorJson(ex.toString().replaceAll("\n", "<br/>"))));
            }

        } catch (Exception e) {
            logger.error("操作异常", ex);
        }
        return mv;
    }

}
