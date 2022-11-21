package kz.nkoldassov.nerdapi.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextSource {

    public static ApplicationContext create() {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanConfigRecreate.class);
        context.refresh();

        return context;
    }

    public static void withContext(ApplicationContextConsumer consumer) {

        ApplicationContext context = create();

        try {

            consumer.accept(context);

        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
