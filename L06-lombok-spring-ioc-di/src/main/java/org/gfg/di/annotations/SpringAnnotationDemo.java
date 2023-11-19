package org.gfg.di.annotations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAnnotationDemo {
    public static void main(String[] args) {
        ApplicationContext factory = new AnnotationConfigApplicationContext("org.gfg.di.annotations");
        NotificationService notificationService = factory.getBean("notificationService", NotificationService.class);

        NotificationService notificationService1 = factory.getBean("notificationService", NotificationService.class);
        System.out.println(notificationService == notificationService1);

        // Not bean
        NotificationService notificationService2 = new NotificationService();
        System.out.println(notificationService == notificationService2);

        notificationService.sendNotification("OTP:1009");
    }
}
