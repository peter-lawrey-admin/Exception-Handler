package net.openhft.exceptionhandler;

/**
 * Created by Peter on 07/04/2016.
 */
public class NPEMain {
    public static void main(String[] args) {
        new StackoverflowExceptionHandler().onException("NPE", new NullPointerException());
    }
}
