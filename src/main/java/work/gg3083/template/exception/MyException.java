package work.gg3083.template.exception;

/**
 * @author Gimi
 * @date 2019/6/8 12:18
 */
public class MyException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String errorCode = MyExceptionType.SYSTEM_ERROR.getErrorCode();

    private String errorMessage = MyExceptionType.SYSTEM_ERROR.getMessage();

    public MyException(MyExceptionType type, Throwable cause) {
        super(type.getMessage(), cause);
        this.errorCode = type.getErrorCode();
        this.errorMessage = type.getMessage();
    }

    public MyException(MyExceptionType type) {
        super(type.getMessage());
        this.errorCode = type.getErrorCode();
        this.errorMessage = type.getMessage();
    }

    public MyException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public MyException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public MyException() {
        super();
    }

    public MyException(String errorCode, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
    }

    public MyException(String errorCode, String errorMessage, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public MyException(Throwable cause) {
        super(cause);
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
