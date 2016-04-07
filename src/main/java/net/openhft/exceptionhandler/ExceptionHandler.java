package net.openhft.exceptionhandler;

/**
 * Created by Peter on 07/04/2016.
 */
@FunctionalInterface
public interface ExceptionHandler {
    void onException(String message, Throwable t);
}
