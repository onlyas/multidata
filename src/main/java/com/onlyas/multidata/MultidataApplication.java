package com.onlyas.multidata;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
@ComponentScan(nameGenerator = MultidataApplication.SpringBeanNameGenerator.class)
public class MultidataApplication {

    public static class SpringBeanNameGenerator extends AnnotationBeanNameGenerator {
        @Override
        protected String buildDefaultBeanName(BeanDefinition definition) {
            return definition.getBeanClassName();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(MultidataApplication.class, args);
    }

}
