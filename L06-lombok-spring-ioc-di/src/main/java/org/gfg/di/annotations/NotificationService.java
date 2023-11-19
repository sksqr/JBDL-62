package org.gfg.di.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("notificationService")
public class NotificationService {

    private SMSGateWayService smsGateWayService;

//    public NotificationService(SMSGateWayService smsGateWayService) {
//        System.out.println("Creating NotificationService with :"+smsGateWayService);
//        this.smsGateWayService = smsGateWayService;
//    }


    @Autowired
    public void setSmsGateWayService(SMSGateWayService smsGateWayService) {
        this.smsGateWayService = smsGateWayService;
    }

    public void sendNotification(String msg){
        smsGateWayService.sendSMS(msg);
    }
}
