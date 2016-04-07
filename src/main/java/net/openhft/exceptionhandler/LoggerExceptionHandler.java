package net.openhft.exceptionhandler;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Peter on 07/04/2016.
 */
public class LoggerExceptionHandler implements ExceptionHandler {
    public static final ExceptionHandler INSTANCE = new LoggerExceptionHandler(java.util.logging.Logger.getAnonymousLogger(), Level.WARNING);
    private final Logger logger;
    private final Level level;

    public LoggerExceptionHandler(Logger logger, Level level) {
        this.logger = logger;
        this.level = level;
    }

    public void onException(String message, Throwable t) {
        logger.log(level, message, t);
    }
}
