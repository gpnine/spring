package com.zcl.study.spring.postprocessor;

import com.zcl.study.spring.model.Y;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class PersonBeanFactoryPostprocessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        GenericBeanDefinition x = (GenericBeanDefinition) beanFactory.getBeanDefinition("x");
        x.setBeanClass(Y.class);
    }
}
