package work.gg3083.template.exception;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import work.gg3083.template.entity.json.JsonBack;

import java.util.Map;

/***
 *
 * 捕获异常
 * @author Gimi
 * @date 2019-06-19 21:37:07
 *
 ***/
@Configuration
@RestControllerAdvice("work.gg3083.template")
public class TraceExceptionController extends ResponseEntityExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(TraceExceptionController.class);

    public TraceExceptionController() {
        logger.info("init - TraceExceptionController");
    }


    /**
     * TODO 参数验证不生效 改用下面方法
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException exp, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String parameterName = exp.getParameterName();
        JsonBack jsonBack = new JsonBack(JsonBack.JSON_BACK_FAILED, MyExceptionType.VALIDATE_ERR.getErrorCode(), "参数" + parameterName + "输入有误", "missing.request.parameter:" + parameterName);
        return new ResponseEntity<>(jsonBack, HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exp, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.info("errorJson.Accept:{},Content-Type:{},exp:{}",new Object[]{request.getHeader("Accept"),request.getHeader("Content-Type"),exp==null?null:exp.getMessage()});
        logger.info("errorJson.exp",exp);

        JsonBack jsonBack = null;
        Map<String, Object> _err = null;
        if(exp!=null && exp instanceof MyException){
            jsonBack = new JsonBack((MyException)exp);

        }else if(exp!=null && exp instanceof MissingServletRequestParameterException) {
            String parameterName = ((MissingServletRequestParameterException) exp).getParameterName();
            jsonBack = new JsonBack(JsonBack.JSON_BACK_FAILED, MyExceptionType.VALIDATE_ERR.getErrorCode(), "参数" + parameterName + "输入有误", "missing.request.parameter:" + parameterName);
        } else if (exp != null && exp instanceof MethodArgumentNotValidException){
            String errMsg = "请求参数验证失败";
            try {
                errMsg = ((MethodArgumentNotValidException)exp).getBindingResult().getAllErrors().get(0).getDefaultMessage();
            }catch (Exception e){}
            jsonBack = new JsonBack(JsonBack.JSON_BACK_FAILED, MyExceptionType.VALIDATE_ERR.getErrorCode(), errMsg, exp.getMessage());
        } else{
            String msgVal = null;
            try {
                msgVal = exp.getCause()==null?exp.getMessage():exp.getCause().getMessage();
            } catch (Exception e) {
                jsonBack = new JsonBack(JsonBack.JSON_BACK_FAILED, MyExceptionType.VALIDATE_ERR.getErrorCode(), msgVal, exp.getMessage());
            }
            logger.error("errorJson.errdata is:{}", JSON.toJSONString(_err));
        }

        return new ResponseEntity<>(jsonBack,HttpStatus.OK);

    }

}