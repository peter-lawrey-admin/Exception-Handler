package net.openhft.exceptionhandler;

/**
 * Created by Peter on 07/04/2016.
 */
public class GoogleExceptionHandler extends WebExceptionHandler {
    public static final ExceptionHandler INSTANCE = new GoogleExceptionHandler(LoggerExceptionHandler.INSTANCE);

    public GoogleExceptionHandler(ExceptionHandler fallBack) {
        super("Google.properties", fallBack);
    }
}
