package net.openhft.exceptionhandler;

/**
 * Created by Peter on 07/04/2016.
 */
public class NPEMain {
    public static void main(String[] args) {
        GoogleExceptionHandler.INSTANCE.onException("I don't know what", new NullPointerException());
//        StackoverflowExceptionHandler.INSTANCE.onException("I don't know what", new NullPointerException());
    }
}
