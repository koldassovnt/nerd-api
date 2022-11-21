package kz.nkoldassov.nerdapi.util;

import org.springframework.context.ApplicationContext;

public interface ApplicationContextConsumer {
    void accept(ApplicationContext context) throws Exception;
}
