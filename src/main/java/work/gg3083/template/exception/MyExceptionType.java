package work.gg3083.template.exception;

/**
 * @author Gimi
 * @date 2019/6/8 11:47
 */
public enum MyExceptionType {
    VALIDATE_ERR("400","参数有误！"),
    SYSTEM_ERROR( "500" , "系统异常"),
    NO_PERMISSION( "501" , "您没有操作权限"),
    TOKEN_EXPIRED("502","token过期！"),
    TOKEN_FAIL("503","token无效！"),
    TOKEN_EMPTY("504","token不能为空！"),
    MAC_ERROR("505","设备信息不正确!"),
    GOLD_LIMIT("506","您获取的金币已超限额,请联系客服!"),
    AMOUNT_LIMIT("507","您获取的积分已超限额,请联系客服!");


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
