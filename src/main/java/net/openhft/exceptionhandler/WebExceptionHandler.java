package net.openhft.exceptionhandler;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;

/**
 * Created by Peter on 07/04/2016.
 */
public class WebExceptionHandler implements ExceptionHandler {
    private final Properties properties = new Properties();

    private final ExceptionHandler fallBack;
    private final String baseURI;

    public WebExceptionHandler(String propertiesFile, ExceptionHandler fallBack) {
        assert fallBack != null;
        this.fallBack = fallBack;
        InputStream stream = WebExceptionHandler.class.getResourceAsStream(propertiesFile);
        try {
            if (stream != null)
                properties.load(stream);
        } catch (IOException e) {
            LoggerExceptionHandler.INSTANCE.onException("Unable to load " + propertiesFile, e);
        }
        baseURI = properties.getProperty("baseUri", "http://stackoverflow.com/search?q=%5Bjava%5D");
    }

    public void onException(String message, Throwable t) {
        try {
            if (Desktop.isDesktopSupported()) {
                while (t.getCause() != null && t.getCause() != t)
                    t = t.getCause();
                String uri = properties.getProperty(t.getClass().getName());
                if (uri == null) {
                    uri = baseURI;
                    String version = System.getProperty("java.version");
                    if (version.compareTo("1.5") >= 0) {
                        String[] parts = version.split("\\.");
                        version = parts[1];
                    }

                    uri += "+" + version + "+" + URIEncoder.encodeURI(t.toString());
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
