package net.openhft.exceptionhandler;

import java.math.BigInteger;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * The exception will be handled by the {@link LoggerExceptionHandler} when the
 * test is executed
 * as<br><code>mvn test -Dtest=DebugExceptionHandlerTest</code><br>
 * <br>
 * The exception will be handled by the {@link GoogleExceptionHandler} when the
 * test is executed
 * as<br><code>mvn test -Dtest=DebugExceptionHandlerTest -Dtest.args=-Djdwp=debug</code>
 *
 * @author Frank Dietrich
 */
public class DebugExceptionHandlerTest {

    @Test
    public void test() {
        ExceptionHandler handler = new DebugExceptionHandler(
                LoggerExceptionHandler.INSTANCE,
                GoogleExceptionHandler.INSTANCE);
        try {
            Object o = 42L;
            BigInteger bi = (BigInteger) o;
            assertTrue(true);
        } catch (ClassCastException e) {
            handler.onException(e.getMessage(), e);
            fail(e.getMessage());
        }
    }
}
