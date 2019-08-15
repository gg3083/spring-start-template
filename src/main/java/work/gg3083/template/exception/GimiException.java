package work.gg3083.template.exception;

/**
 * @author Gimi
 * @date 2019/6/8 12:18
 */
public class GimiException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String errorCode = GimiExceptionType.SYSTEM_ERROR.getErrorCode();

    private String errorMessage = GimiExceptionType.SYSTEM_ERROR.getMessage();

    public GimiException(GimiExceptionType type, Throwable cause) {
        super(type.getMessage(), cause);
        this.errorCode = type.getErrorCode();
        this.errorMessage = type.getMessage();
    }

    public GimiException(GimiExceptionType type) {
        super(type.getMessage());
        this.errorCode = type.getErrorCode();
        this.errorMessage = type.getMessage();
    }

    public GimiException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public GimiException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public GimiException() {
        super();
    }

    public GimiException(String errorCode, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
    }

    public GimiException(String errorCode, String errorMessage, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public GimiException(Throwable cause) {
        super(cause);
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
