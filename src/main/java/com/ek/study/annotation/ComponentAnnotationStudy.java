package com.ek.study.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Set;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/7/5
 */
@Configuration
public class ComponentAnnotationStudy {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(InjectClass.class);
        annotationConfigApplicationContext.refresh();
        InjectClass injectClass = annotationConfigApplicationContext.getBean(InjectClass.class);
        injectClass.print();
    }

}

@MyAnnotation
class InjectClass {
    public void print() {
        System.out.println("hello world");
    }
}

class BeanScannerConfigurer implements BeanFactoryPostProcessor, ApplicationContextAware {

    ApplicationContext applicationContext;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Scanner scanner = new Scanner((BeanDefinitionRegistry) beanFactory);
        scanner.setResourceLoader(this.applicationContext);
        scanner.scan(this.getClass().getPackage().getName());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

final class Scanner extends ClassPathBeanDefinitionScanner {
    public Scanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public void registerDefaultFilters() {
        this.addIncludeFilter(new AnnotationTypeFilter(MyAnnotation.class));
    }

    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        for (BeanDefinitionHolder holder : beanDefinitions) {
            GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
            definition.getPropertyValues().add("innerClassName", definition.getBeanClassName());
            definition.setBeanClass(MyFactoryBeanTest.class);
        }
        return beanDefinitions;
    }

    public boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return super.isCandidateComponent(beanDefinition) && beanDefinition.getMetadata()
                .hasAnnotation(MyAnnotation.class.getName());
    }
}

class MyFactoryBeanTest<T> implements InitializingBean, FactoryBean<T> {
    private String innerClassName;

    public void setInnerClassName(String innerClassName) {
        this.innerClassName = innerClassName;
    }

    public T getObject() throws Exception {
        Class innerClass = Class.forName(innerClassName);
        if (innerClass.isInterface()) {
            return (T) InterfaceProxy.newInstance(innerClass);
        } else {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(innerClass);
            enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
            enhancer.setCallback(new MethodInterceptorImpl());
            return (T) enhancer.create();
        }
    }

    public Class<?> getObjectType() {
        try {
            return Class.forName(innerClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws Exception {
    }
}

class InterfaceProxy implements InvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("ObjectProxy execute:" + method.getName());
        return method.invoke(proxy, args);
    }

    public static <T> T newInstance(Class<T> innerInterface) {
        ClassLoader classLoader = innerInterface.getClassLoader();
        Class[] interfaces = new Class[]{innerInterface};
        InterfaceProxy proxy = new InterfaceProxy();
        return (T) Proxy.newProxyInstance(classLoader, interfaces, proxy);
    }
}

class MethodInterceptorImpl implements MethodInterceptor {
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("MethodInterceptorImpl:" + method.getName());
        return methodProxy.invokeSuper(o, objects);
    }
}