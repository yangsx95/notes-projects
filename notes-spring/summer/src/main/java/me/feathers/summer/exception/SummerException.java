package me.feathers.summer.exception;

public class SummerException extends RuntimeException {

    public SummerException() {
        super();
    }

    public SummerException(String message) {
        super(message);
    }

    public SummerException(String message, Throwable cause) {
        super(message, cause);
    }

    public SummerException(Throwable cause) {
        super(cause);
    }

    protected SummerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
