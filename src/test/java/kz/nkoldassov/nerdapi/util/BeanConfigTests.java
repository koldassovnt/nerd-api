package kz.nkoldassov.nerdapi.util;

import kz.nkoldassov.nerdapi.beans.all.BeanConfigAll;
import kz.nkoldassov.nerdapi.beans.for_tests.BeanConfigTestBeans;
import kz.nkoldassov.nerdapi.beans.security.BeanConfigSecurity;
import kz.nkoldassov.nerdapi.dao_for_tests.BeanConfigDaoForTests;
import kz.nkoldassov.nerdapi.in_service.BeanConfigTestInService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan
@Import({
        BeanConfigAll.class,
        BeanConfigTestBeans.class,
        BeanConfigSecurity.class,
        BeanConfigTestInService.class,
        BeanConfigDaoForTests.class
})
public class BeanConfigTests {
}
