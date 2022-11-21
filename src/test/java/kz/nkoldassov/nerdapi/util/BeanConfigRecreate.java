package kz.nkoldassov.nerdapi.util;


import kz.nkoldassov.nerdapi.beans.all.BeanConfigAll;
import kz.nkoldassov.nerdapi.beans.factory.BeanConfigFactory;
import kz.nkoldassov.nerdapi.beans.for_recreate.BeanConfigRecreateBeans;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan
@Import({
        BeanConfigRecreateBeans.class,
        BeanConfigAll.class,
        BeanConfigFactory.class
})
public class BeanConfigRecreate { }
