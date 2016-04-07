package net.openhft.exceptionhandler;

/**
 * Created by Peter on 07/04/2016.
 */
public class StackoverflowExceptionHandler extends WebExceptionHandler {
    public static final ExceptionHandler INSTANCE = new StackoverflowExceptionHandler(LoggerExceptionHandler.INSTANCE);

    public StackoverflowExceptionHandler(ExceptionHandler fallBack) {
        super("Stackoverflow.properties", fallBack);
    }
}
