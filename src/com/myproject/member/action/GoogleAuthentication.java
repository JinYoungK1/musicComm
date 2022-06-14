package com.myproject.member.action;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class GoogleAuthentication extends Authenticator{

	PasswordAuthentication passAuth;
    
    public GoogleAuthentication(){
        passAuth = new PasswordAuthentication("rlawlsdud211@gmail.com", "dsxqpmbjvpeodemb");
    }
 
    public PasswordAuthentication getPasswordAuthentication() {
        return passAuth;
    }
}
