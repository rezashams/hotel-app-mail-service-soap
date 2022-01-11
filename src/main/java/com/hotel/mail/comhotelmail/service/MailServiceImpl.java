/*
 * Copyright (c) 2021 Birmingham City University. All rights reserved.
 * Author:  Reza Shams (rezashams86@gmail.com)
 */
package com.hotel.mail.comhotelmail.service;

import com.hotel.mail.comhotelmail.model.Email;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    private final MailJitService mailJitService;
    private final static String emailFrom ="reza.shams@mail.bcu.ac.uk";
    public MailServiceImpl(MailJitService mailJitService) {
        this.mailJitService = mailJitService;
    }

    @Override
    public int sendEmail(String roomName, int payablePrice, int discountPrice,  String emailAddr) throws MailjetSocketTimeoutException, MailjetException {
        Email email = new Email();
        email.setEmailAddress(emailAddr);
        System.out.println(emailAddr);
        email.setSubject("Invoice Hotel");
        StringBuilder builder = new StringBuilder();
        builder.append("<h1>Invoice</h1>");
        builder.append("<br></br>");
        builder.append("<b>roomName:</b> ");
        builder.append(roomName);
        builder.append("<br></br>");
        builder.append("<b>Discount Price:</b> ");
        builder.append(discountPrice);
        builder.append("<br></br>");
        builder.append("<b>Payable Price:</b> ");
        builder.append(payablePrice);
        builder.append("<br></br>");
        builder.append("<b>Hotel Management System</b>");
        email.setBody(builder.toString());
        return mailJitService.sendEmail(emailFrom,email.getEmailAddress(),email.getSubject(), email.getBody());
    }
}
