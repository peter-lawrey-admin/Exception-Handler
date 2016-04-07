package net.openhft.exceptionhandler;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;

/**
 * Created by Peter on 07/04/2016.
 */
public class StackoverflowExceptionHandler implements ExceptionHandler {
    public static final ExceptionHandler INSTANCE = new StackoverflowExceptionHandler(LoggerExceptionHandler.INSTANCE);

    static final Properties PROPERTIES = new Properties();
    private static final String BASE_URI;

    static {
        InputStream stream = StackoverflowExceptionHandler.class.getResourceAsStream("Stackoverflow.properties");
        try {
            if (stream != null)
                PROPERTIES.load(stream);
        } catch (IOException e) {
            LoggerExceptionHandler.INSTANCE.onException("Unable to load Stackoverflow.properties", e);
        }
        BASE_URI = PROPERTIES.getProperty("baseUri", "http://stackoverflow.com/search?q=%5Bjava%5D");
    }

    final ExceptionHandler fallBack;

    public StackoverflowExceptionHandler(ExceptionHandler fallBack) {
        assert fallBack != null;
        this.fallBack = fallBack;
    }

    public void onException(String message, Throwable t) {
        try {
            if (Desktop.isDesktopSupported()) {
                while (t.getCause() != null && t.getCause() != t)
                    t = t.getCause();
                String uri = PROPERTIES.getProperty(t.getClass().getName());
                if (uri == null) {
                    uri = BASE_URI + "+" + t;
                    if (message != null)
                        uri += "+" + URIEncoder.encodeURI(message);
                }
                Desktop.getDesktop().browse(new URI(uri));
            } else {
                callFallback(message, t);
            }
        } catch (Exception e) {
            callFallback("Failed to open browser", e);
            callFallback(message, t);
        }
    }

    private void callFallback(String message, Throwable t) {
        fallBack.onException(message, t);
    }
}
