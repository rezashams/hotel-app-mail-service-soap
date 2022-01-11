package com.hotel.mail.comhotelmail.service;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;

public interface MailService {

    int sendEmail(String roomName, int payablePrice, int discountPrice, String email) throws MailjetSocketTimeoutException, MailjetException;
}
