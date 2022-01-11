package com.hotel.mail.comhotelmail.service;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;

public interface MailJitService {

    int sendEmail(String from, String to, String subject, String Body) throws MailjetSocketTimeoutException, MailjetException;


}

