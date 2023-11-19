package org.gfg.di;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIocDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("projectbeans.xml");
        Engine engine1 = factory.getBean("engine1",Engine.class);
        System.out.println(engine1);

        Engine engineA = factory.getBean("engine1",Engine.class);
        System.out.println(engineA);
        System.out.println(engine1 ==  engineA);


        Engine engine2 = factory.getBean("engine2",Engine.class);
        System.out.println(engine2);

        System.out.println(engine1 ==  engine2);


        Car car1 = factory.getBean("car1",Car.class);
        System.out.println(car1);

        factory.close();

    }
}
