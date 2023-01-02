package kz.nkoldassov.nerdapi.util;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(classes = BeanConfigTests.class)
public abstract class ParentTestNg extends AbstractTestNGSpringContextTests {
}
