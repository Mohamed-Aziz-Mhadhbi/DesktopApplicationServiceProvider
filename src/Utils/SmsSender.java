/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import static Utils.SmsSender.ACCOUNT_SID;
import static Utils.SmsSender.*;

import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.net.URI;
import java.util.Arrays;

/**
 *
 * @author LENOVO
 */
    public class SmsSender {
    

     /**
     */
   // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID =
            "AC39ba69eb1f4e4622b672ac8ec975bd57";
    public static final String AUTH_TOKEN =
            "eb2fff1574304bad98125f051bf6a9bf";


    public void send(String s,String x){
         Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
       Message message = Message 
                .creator(new PhoneNumber("+21652353170"), // to
                        new PhoneNumber("+14076244917"), // from
                       ""+ s)
                .create();
  System.out.println("aslema");
        System.out.println(message.getSid());
    
}
}