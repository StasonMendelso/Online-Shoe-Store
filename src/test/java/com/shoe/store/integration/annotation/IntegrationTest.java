package com.shoe.store.integration.annotation;

import com.shoe.store.integration.TestApplicationRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Stanislav Hlova
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = TestApplicationRunner.class)
@Transactional
@ActiveProfiles("test")
@AutoConfigureMockMvc
public @interface IntegrationTest {

}
