package kz.nkoldassov.nerdapi.beans.all;

import kz.nkoldassov.nerdapi.impl.BeanConfigRegister;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan
@Import({BeanConfigRegister.class})
public class BeanConfigAll {
}
