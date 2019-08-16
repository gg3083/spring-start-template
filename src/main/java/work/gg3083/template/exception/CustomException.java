package work.gg3083.template.exception;

/**
 * @author Gimi
 * @date 2019/6/8 12:18
 */
public class CustomException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String errorCode = CustomExceptionType.SYSTEM_ERROR.getErrorCode();

    private String errorMessage = CustomExceptionType.SYSTEM_ERROR.getMessage();

    public CustomException(CustomExceptionType type, Throwable cause) {
        super(type.getMessage(), cause);
        this.errorCode = type.getErrorCode();
        this.errorMessage = type.getMessage();
    }

    public CustomException(CustomExceptionType type) {
        super(type.getMessage());
        this.errorCode = type.getErrorCode();
        this.errorMessage = type.getMessage();
    }

    public CustomException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public CustomException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public CustomException() {
        super();
    }

    public CustomException(String errorCode, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
    }

    public CustomException(String errorCode, String errorMessage, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
