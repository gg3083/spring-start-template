package work.gg3083.template.entity.json;

import lombok.Data;
import work.gg3083.template.exception.MyException;
import work.gg3083.template.exception.MyExceptionType;

/**
 * @author Gimi
 * @date 2019/6/8 11:44
 */
@Data
public class JsonBack {
    //0成功 1失败
    private int code;

    //错误码
    private String errorCode;

    private String message;

    private Object obj;

    public static final int JSON_BACK_SUCCESS = 0;
    public static final int JSON_BACK_FAILED = 1;

    public JsonBack() {
        this.code = JSON_BACK_SUCCESS;
    }

    public JsonBack(int code, String errorCode, String message, Object obj) {
        this.code = code;
        this.errorCode = errorCode;
        this.message = message;
        this.obj = obj;
    }

    public JsonBack(MyException e) {
        this.code = JSON_BACK_FAILED;
        this.errorCode = e.getErrorCode();
        this.message = e.getErrorMessage();
        this.obj = obj;
    }

    public void buildErrorJsonBack(String msg) {
        setCode(JSON_BACK_FAILED);
        setErrorCode(MyExceptionType.SYSTEM_ERROR.getErrorCode());
        setMessage(msg);
    }

    public void buildErrorJsonBack() {
        setCode(JSON_BACK_FAILED);
        setErrorCode(MyExceptionType.SYSTEM_ERROR.getErrorCode());
        setMessage(MyExceptionType.SYSTEM_ERROR.getMessage());
    }

    public void buildErrorJsonBack(MyExceptionType type) {
        setCode(JSON_BACK_FAILED);
        setErrorCode(type.getErrorCode());
        setMessage(type.getMessage());
    }

    public void buildSuccJsonBack(Object obj) {
        setCode(JSON_BACK_SUCCESS);
        setObj(obj);
    }

    public void buildSuccJsonBack(Object obj, String msg) {
        setCode(JSON_BACK_SUCCESS);
        setObj(obj);
        setMessage(msg);
    }

    public void buildSuccJsonBack() {
        setCode(JSON_BACK_SUCCESS);
    }

    public static JsonBack buildSuccJson() {
        return new JsonBack(JSON_BACK_SUCCESS, null, "", null);
    }
    public static JsonBack buildSuccJson(Object obj) {
        return new JsonBack(JSON_BACK_SUCCESS, null, "", obj);
    }

    public static JsonBack buildSuccJson(Object obj, String msg) {
        return new JsonBack(JSON_BACK_SUCCESS, null, msg, obj);
    }

    public static JsonBack buildErrorJson(String msg) {
        return new JsonBack(JSON_BACK_FAILED, MyExceptionType.SYSTEM_ERROR.getErrorCode(), msg, null);
    }

    public static JsonBack buildErrorJson(String errorCode, String msg) {
        return new JsonBack(JSON_BACK_FAILED, MyExceptionType.SYSTEM_ERROR.getErrorCode(), msg, null);
    }

    public static JsonBack buildErrorJson(MyExceptionType type) {
        return new JsonBack(JSON_BACK_FAILED, type.getErrorCode(), type.getMessage(), null);
    }

    public static JsonBack buildErrorJson(MyException e) {
        return new JsonBack(JSON_BACK_FAILED, e.getErrorCode(), e.getMessage(), null);
    }


    public static JsonBack buildErrorJson(Object obj, String msg) {
        return new JsonBack(JSON_BACK_FAILED, MyExceptionType.SYSTEM_ERROR.getErrorCode(), msg, obj);
    }


}
