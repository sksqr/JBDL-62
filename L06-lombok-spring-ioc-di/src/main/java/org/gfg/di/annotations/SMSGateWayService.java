package org.gfg.di.annotations;

import org.springframework.stereotype.Component;

@Component("sMSGateWayService")
public class SMSGateWayService {

    public SMSGateWayService() {
        System.out.println("creating SMSGateWayService");
    }

    public void sendSMS(String msg){
        System.out.println("Sending:"+msg);
    }
}
