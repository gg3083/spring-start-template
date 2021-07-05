package work.gg3083.template.exception;

/**
 * @author Gimi
 * @date 2019/6/8 11:47
 */
public enum MyExceptionType {
    VALIDATE_ERR("400","参数有误！"),
    SYSTEM_ERROR( "500" , "系统异常"),
    NO_PERMISSION( "1000" , "您没有操作权限"),
    TOKEN_EXPIRED("1001","token过期！"),
    TOKEN_FAIL("1002","token无效！"),
    NOT_SESSION("1003","登录信息已过期!"),
    TOKEN_EMPTY("1004","token不能为空！");


    private String errorCode;
    private String message;

    MyExceptionType(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
