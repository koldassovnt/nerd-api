package kz.nkoldassov.nerdapi.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class LOG {

    private final Logger logger;

    private LOG(Logger logger) {
        this.logger = logger;
    }

    public static LOG forClass(Class<?> aClass) {
        return new LOG(LoggerFactory.getLogger(aClass));
    }

    public static LOG byName(String name) {
        return new LOG(LoggerFactory.getLogger(name));
    }

    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public void trace(Supplier<String> message) {
        if (isTraceEnabled()) {
            logger.trace(message.get());
        }
    }

    public void debug(Supplier<String> message) {
        if (isDebugEnabled()) {
            logger.debug(message.get());
        }
    }

    public void info(Supplier<String> message) {
        if (isInfoEnabled()) {
            logger.info(message.get());
        }
    }

    public void error(String message, Throwable throwable) {
        if (isErrorEnabled()) {
            logger.error(message, throwable);
        }
    }

    public void error(String message) {
        if (isErrorEnabled()) {
            logger.error(message);
        }
    }

    public void errorOnly(Throwable throwable) {
        if (isErrorEnabled()) {
            if (throwable == null) {
                logger.error("throwable == null");
            } else {
                logger.error(throwable.getMessage(), throwable);
            }
        }
    }

    public static void resetThread() {
        LogIdentity.resetThread();
    }
}
