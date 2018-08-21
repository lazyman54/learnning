package com.ek.study.beancopyperformance;

import net.sf.cglib.beans.BeanCopier;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class performanceCompare {

    private static IBeanCopy apache = new ApacheBeanCopy();
    private static IBeanCopy cglib = new CglibBeanCopy();
    private static IBeanCopy javaSetter = new JavaSetterBeanCopy();
    private static IBeanCopy spring = new SpringBeanCopy();

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InterruptedException {

        SourceBean sourceBean = new SourceBean();
        sourceBean.setAddr("addr");
        sourceBean.setAge(8);
        sourceBean.setName("eric");
        sourceBean.setSex("boy");

        TargetBean targetBean = new TargetBean();

        //singleThreadTest(10000, sourceBean, targetBean);

        multiThreadTest(10, sourceBean, targetBean, apache);


    }

    public static void singleThreadTest(int loopCount, SourceBean sourceBean, TargetBean targetBean) throws InvocationTargetException, IllegalAccessException {
        System.out.println("**********apache******************");
        long level0 = System.nanoTime();
        for (int i = 0; i < loopCount; i++) {
            apache.copyBean(sourceBean, targetBean);
        }
        long level1 = System.nanoTime();
        System.out.println("**********apache******************");
        System.out.println("**********cglib******************");
        for (int i = 0; i < loopCount; i++) {
            cglib.copyBean(sourceBean, targetBean);
        }
        long level2 = System.nanoTime();
        System.out.println("**********java setter******************");
        for (int i = 0; i < loopCount; i++) {
            javaSetter.copyBean(sourceBean, targetBean);
        }
        long level3 = System.nanoTime();
        System.out.println("**********spring******************");
        for (int i = 0; i < loopCount; i++) {
            spring.copyBean(sourceBean, targetBean);
        }
        long level4 = System.nanoTime();

        System.out.println("apache cost: " + (level1 - level0) / 1000 / loopCount + "ms");
        System.out.println("cglib cost: " + (level2 - level1) / 1000 / loopCount + "ms");
        System.out.println("java setter cost: " + (level3 - level2) / 1000 / loopCount + "ms");
        System.out.println("spring cost: " + (level4 - level3) / 1000 / loopCount + "ms");

    }

    public static void multiThreadTest(int loopCount, SourceBean sourceBean, TargetBean targetBean, IBeanCopy beanCopy) throws InvocationTargetException, IllegalAccessException, InterruptedException {


        ExecutorService executorService = Executors.newFixedThreadPool(100);

        CountDownLatch countDownLatch = new CountDownLatch(loopCount);
        long current = System.nanoTime();

        for (int i = 0; i < loopCount; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        beanCopy.copyBean(sourceBean, targetBean);
                    } catch (InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        countDownLatch.await();
        System.out.println(beanCopy + " cost: " + (System.nanoTime() - current) / 1000 / loopCount + "ms");


    }


}

class SourceBean {
    private String name;
    private int age;
    private String sex;
    private String addr;

    @Override
    public String toString() {
        return "SourceBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}

class TargetBean {
    private String name;
    private int age;
    private String sex;
    private String addr;

    @Override
    public String toString() {
        return "TargetBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}

class ApacheBeanCopy implements IBeanCopy {
    @Override
    public void copyBean(Object source, Object target) throws InvocationTargetException, IllegalAccessException {

        long currentTime = System.nanoTime();
        org.apache.commons.beanutils.BeanUtils.copyProperties(source, target);
        System.out.println("apache bean copy cost: " + (System.nanoTime() - currentTime) / 1000 + "micos");

    }
}

class CglibBeanCopy implements IBeanCopy {

    private BeanCopier beanCopier = BeanCopier.create(SourceBean.class, TargetBean.class, false);

    @Override
    public void copyBean(Object source, Object target) {
        long currentTime = System.nanoTime();
        beanCopier.copy(source, target, null);
        System.out.println("cglib bean copy cost: " + (System.nanoTime() - currentTime) / 1000 + "micos");
    }
}

class SpringBeanCopy implements IBeanCopy {
    @Override
    public void copyBean(Object source, Object target) {
        long currentTime = System.nanoTime();
        BeanUtils.copyProperties(source, target);
        System.out.println("spring bean copy cost: " + (System.nanoTime() - currentTime) / 1000 + "micos");
    }
}

class JavaSetterBeanCopy implements IBeanCopy {
    @Override
    public void copyBean(Object source, Object target) {
        long currentTime = System.nanoTime();
        SourceBean sourceBean = (SourceBean) source;
        TargetBean targetBean = (TargetBean) target;
        targetBean.setAddr(sourceBean.getAddr());
        targetBean.setAge(sourceBean.getAge());
        targetBean.setName(sourceBean.getName());
        targetBean.setSex(sourceBean.getSex());
        System.out.println("javaSetter bean copy cost: " + (System.nanoTime() - currentTime) / 1000 + "micos");

    }
}